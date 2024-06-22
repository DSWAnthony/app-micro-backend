package com.app.exa.infrastructure.out.persistence.adapters;

import com.app.exa.app.ports.out.BookRepositoryPort;
import com.app.exa.cloud.CloudService;
import com.app.exa.domain.entities.BookEntity;
import com.app.exa.domain.entities.CategoriesEntity;
import com.app.exa.domain.models.Book;
import com.app.exa.domain.models.Respon;
import com.app.exa.infrastructure.out.persistence.mappers.BookMapper;
import com.app.exa.infrastructure.out.persistence.repositorys.BookRepository;
import com.app.exa.infrastructure.out.persistence.repositorys.CategorieRepository;
import io.quarkus.hibernate.reactive.panache.common.WithSession;
import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaDelete;
import jakarta.persistence.criteria.Root;
import jakarta.transaction.Transactional;
import org.hibernate.reactive.mutiny.Mutiny;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@ApplicationScoped
public class BookRepositoryAdapter implements BookRepositoryPort {
    @Inject
    BookRepository bookRepository;
    @Inject
    CategorieRepository categorieRepository;
    @Inject
    Mutiny.SessionFactory sessionFactory;

    @WithSession
    public Uni<List<Book>> getAllBooks() {
        return bookRepository.listAll()
                .onItem().transform(books -> books.stream()
                        .map(BookMapper::entityToDomain)
                        .collect(Collectors.toList()));
    }

    @Transactional
    @WithSession
    public Uni<Book> createBook(Book book,Long categorie_id) {
        BookEntity bookEntity = BookMapper.domainToEntity(book);
        CloudService cloudService =new CloudService();
        return categorieRepository.findById(categorie_id)
                .flatMap(categoriesEntity -> {
                    Map result =cloudService.uploadPdf(bookEntity.getStorage_url());
                    String url = (String) result.get("secure_url");
                    bookEntity.setStorage_url(url);
                    System.out.println(result);
                    bookEntity.setCategories(categoriesEntity);
                    return bookRepository.persistAndFlush(bookEntity)
                            .map(BookMapper::entityToDomain);
                });
    }

    @WithSession
    public Uni<Book> getBookById(Long id) {
        return bookRepository.findById(id)
                .map(BookMapper::entityToDomain);
    }

    @Override
    public Uni<Respon> deleteBookById(Long id) {
        return sessionFactory.withTransaction((session, transaction) -> {
            CriteriaBuilder criteriaBuilder = sessionFactory.getCriteriaBuilder();
            CriteriaDelete<BookEntity> deleteQuery = criteriaBuilder.createCriteriaDelete(BookEntity.class);

            Root<BookEntity> root = deleteQuery.from(BookEntity.class);
            deleteQuery.where(criteriaBuilder.equal(root.get("id"),id));

            return session.createQuery(deleteQuery).executeUpdate()
                    .map(updatedCount -> {
                        if (updatedCount > 0){
                            return new Respon("Ok","Successfully");
                        }else {
                            return new Respon("Failed","Error Id Not Exists");
                        }

                    });
        });
    }

    @Transactional
    @WithSession
    public Uni<Book> updateBook(Book book,Long categorie_id) {
        return bookRepository.findById(book.getId())
                .onItem().ifNotNull().transformToUni(bookEntity ->
                        // Buscar la categoría
                        categorieRepository.findById(categorie_id)
                                .onItem().ifNotNull().transformToUni(categoryEntity -> {
                                    bookEntity.setAuthor(book.getAuthor());
                                    bookEntity.setTitle(book.getTitle());
                                    bookEntity.setPages(book.getPages());
                                    bookEntity.setCategories(categoryEntity);
                                    bookEntity.setImg_url(book.getImg_url());
                                    bookEntity.setStorage_url(book.getStorage_url());
                                    bookEntity.setDescription(book.getDescription());
                                    return bookRepository.persistAndFlush(bookEntity)
                                            .map(BookMapper::entityToDomain);
                                })
                                .onItem().ifNull().failWith(new IllegalArgumentException("Category not found"))
                )
                .onItem().ifNull().failWith(new IllegalArgumentException("Book not found"));
    }

}
