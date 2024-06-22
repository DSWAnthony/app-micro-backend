package com.app.exa.app.ports.usecases;

import com.app.exa.app.ports.in.CreateFavoriteUseCase;
import com.app.exa.app.ports.out.FavoriteRepositoryPort;
import com.app.exa.domain.models.Favorite;
import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class CreateFavoriteUseCaseImpl implements CreateFavoriteUseCase {
    @Inject
    FavoriteRepositoryPort favoriteRepositoryPort;

    @Override
    public Uni<Favorite> createFavorite(Long user_id ,Long book_id) {
        return favoriteRepositoryPort.createFavorite( user_id , book_id);
    }
}
