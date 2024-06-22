package com.app.exa.infrastructure.out.persistence.repositorys;

import com.app.exa.domain.entities.FavoriteEntity;
import io.quarkus.hibernate.reactive.panache.PanacheRepositoryBase;
import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import org.hibernate.reactive.mutiny.Mutiny;

@ApplicationScoped
public class FavoriteRepository implements PanacheRepositoryBase<FavoriteEntity,Long> {

    @Inject
    Mutiny.SessionFactory sessionFactory;

    public Uni<Boolean> deleteById(Long id) {
        return sessionFactory.withTransaction(session ->
                session.find(FavoriteEntity.class, id)
                        .onItem().ifNotNull().invoke(favorite -> {
                            session.remove(favorite); // Marca la entidad para ser eliminada
                            session.flush(); // Ejecuta la operación de eliminación
                        })
                        .onItem().ifNotNull().transform(favorite -> true) // Devuelve true si el favorito fue encontrado y eliminado
                        .onItem().ifNull().continueWith(false) // Devuelve false si el favorito no fue encontrado
        );
    }
}
