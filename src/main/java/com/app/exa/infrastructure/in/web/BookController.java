package com.app.exa.infrastructure.in.web;

import com.app.exa.app.ports.in.*;
import com.app.exa.domain.models.Book;
import com.app.exa.domain.models.Respon;
import io.smallrye.mutiny.Uni;
import java.util.*;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.eclipse.microprofile.graphql.GraphQLApi;
import org.eclipse.microprofile.graphql.Mutation;
import org.eclipse.microprofile.graphql.Query;

@ApplicationScoped
@GraphQLApi
public class BookController {
    @Inject
    CreateBookUseCase createBookUseCase;
    @Inject
    GetAllBooksUseCase getAllBooksUseCase;
    @Inject
    GetBookByIdUseCase getBookByIdUseCase;
    @Inject
    DeleteBookByIdUseCase deleteBookByIdUseCase;
    @Inject
    UpdateBookUseCase updateBookUseCase;
    @Query("GetAllBooks")
    public Uni<List<Book>> getAllBooks(){
        return getAllBooksUseCase.getAllBooks();
    }

    @Query("GetBookById")
    public Uni<Book> getBookById(Long id){
        return getBookByIdUseCase.getBookById(id);
    }

    @Mutation("CreateBook")
    public Uni<Book> createBook(Book book,Long categorie_id){
        return createBookUseCase.createBook(book,categorie_id);
    }

    @Mutation("DeleteBookByID")
    public Uni<Respon> deleteBookById(Long id){ return deleteBookByIdUseCase.deleteBookById(id);}

    @Mutation("UpdateBook")
    public Uni<Book> updateBook(Book book,Long categorie_id){
        return updateBookUseCase.updateBook(book,categorie_id);
    }
}
