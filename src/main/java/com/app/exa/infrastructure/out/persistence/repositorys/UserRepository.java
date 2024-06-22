package com.app.exa.infrastructure.out.persistence.repositorys;

import com.app.exa.domain.entities.UserEntity;
import io.quarkus.hibernate.reactive.panache.PanacheRepositoryBase;
import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.hibernate.reactive.mutiny.Mutiny;

@ApplicationScoped
public class UserRepository implements PanacheRepositoryBase<UserEntity,Long> {
    @Inject
    Mutiny.SessionFactory sessionFactory;

    public Uni<Boolean> deleteById(Long id){
        return sessionFactory.withTransaction((session, transaction) ->
            session.find(UserEntity.class,id)
                    .onItem().ifNotNull().transformToUni(userEntitie ->
                        session.remove(userEntitie).replaceWith(true)
                    )
                    .onItem().ifNull().continueWith(false)
        );
    }
}