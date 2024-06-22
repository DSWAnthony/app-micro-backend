package com.app.exa.infrastructure.out.persistence.adapters;


import com.app.exa.app.ports.out.FavoriteRepositoryPort;
import com.app.exa.domain.entities.FavoriteEntity;
import com.app.exa.domain.models.Favorite;
import com.app.exa.domain.models.Respon;
import com.app.exa.infrastructure.out.persistence.mappers.FavoriteMapper;
import com.app.exa.infrastructure.out.persistence.repositorys.BookRepository;
import com.app.exa.infrastructure.out.persistence.repositorys.FavoriteRepository;
import com.app.exa.infrastructure.out.persistence.repositorys.UserRepository;
import io.quarkus.hibernate.reactive.panache.common.WithSession;
import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaDelete;
import jakarta.persistence.criteria.Root;
import jakarta.transaction.Transactional;
import org.hibernate.reactive.mutiny.Mutiny;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@ApplicationScoped
public class FavoriteRepositoryAdapter implements FavoriteRepositoryPort {
    @Inject
    FavoriteRepository favoriteRepository;
    @Inject
    UserRepository userRepository;
    @Inject
    BookRepository bookRepository;
    @Inject
    Mutiny.SessionFactory sessionFactory;

    @Transactional
    @WithSession
    public Uni<Favorite> createFavorite(Long user_id ,Long book_id) {
        FavoriteEntity favoriteEntity = new FavoriteEntity();
        favoriteEntity.setDate(LocalDateTime.now());
        return userRepository.findById(user_id)
                .flatMap(userEntity -> {
                    if (userEntity == null) {
                        return Uni.createFrom().failure(new RuntimeException("User not found"));
                    }
                    favoriteEntity.setUserEntity(userEntity);
                    return bookRepository.findById(book_id)
                            .flatMap(bookEntity -> {
                                if (bookEntity == null) {
                                    return Uni.createFrom().failure(new RuntimeException("Book not found"));
                                }
                                favoriteEntity.setBookEntity(bookEntity);
                                return favoriteRepository.persistAndFlush(favoriteEntity)
                                        .map(FavoriteMapper::entityToDomain);
                            });
                });
    }

    @WithSession
    public Uni<List<Favorite>> getAllFavorites() {
        return favoriteRepository.listAll()
                .onItem().transform(favoriteEntity -> favoriteEntity
                        .stream().map(FavoriteMapper::entityToDomain)
                        .collect(Collectors.toList()));
    }

    @WithSession
    public Uni<List<Favorite>> getFavoriteByUserId(Long id) {
        return userRepository.findById(id)
                .flatMap(userEntity -> {
                    List<Favorite> favorites = userEntity.getFavorites()
                            .stream().map(FavoriteMapper::entityToDomain)
                            .collect(Collectors.toList());
                    return Uni.createFrom().item(favorites);
                });
    }

    @Transactional
    @WithSession
    public Uni<Respon> deleteFavoriteByUser(Long bookId, Long userId) {
        return userRepository.findById(userId)
                .flatMap(userEntity -> {
                    // Find the favorite to delete
                    Optional<FavoriteEntity> favoriteOptional = userEntity.getFavorites().stream()
                            .filter(favoriteEntity -> favoriteEntity.getBookEntity().getId().equals(bookId))
                            .findFirst();

                    if (favoriteOptional.isPresent()) {
                        FavoriteEntity favoriteToDelete = favoriteOptional.get();
                        userEntity.getFavorites().remove(favoriteToDelete); // Remove from user's favorites
                        favoriteToDelete.getBookEntity().getFavorites().remove(favoriteToDelete); // Remove from book's favorites
                        // Delete the favorite entity from the database
                        return deleFavoriteById(favoriteToDelete.getId())
                                .map(deleted -> new Respon("SUCCESS", "Favorite deleted successfully"));
                    } else {
                        return Uni.createFrom().item(new Respon("ERROR", "Favorite not found"));
                    }
                });
    }

    @Transactional
    public Uni<Respon> deleFavoriteById(Long id) {
        return sessionFactory.withTransaction((session, transaction) -> {
            // Usamos el CriteriaBuilder desde sessionFactory
            CriteriaBuilder criteriaBuilder = sessionFactory.getCriteriaBuilder();
            CriteriaDelete<FavoriteEntity> deleteQuery = criteriaBuilder.createCriteriaDelete(FavoriteEntity.class);

            // Definimos el root y la condición de eliminación
            Root<FavoriteEntity> root = deleteQuery.from(FavoriteEntity.class);
            deleteQuery.where(criteriaBuilder.equal(root.get("id"), id));

            // Ejecutamos la consulta de eliminación y manejamos el resultado
            return session.createQuery(deleteQuery).executeUpdate()
                    .map(updatedCount -> { 
                        if (updatedCount > 0) {
                            return new Respon("SUCCESS","Favorite deleted successfully"); // Indica que la eliminación fue exitosa
                        } else {
                            throw new RuntimeException("Failed to delete entity with id: " + id);
                        }
                    });
        });
    }

}
