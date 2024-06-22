package com.app.exa.app.ports.usecases;

import com.app.exa.app.ports.in.GetAllFavoriteUseCase;
import com.app.exa.app.ports.out.FavoriteRepositoryPort;
import com.app.exa.domain.models.Favorite;
import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.util.List;

@ApplicationScoped
public class GetAllFavoriteUseCaseImpl implements GetAllFavoriteUseCase {
    @Inject
    FavoriteRepositoryPort favoriteRepositoryPort;

    @Override
    public Uni<List<Favorite>> getAllFavorite() {
        return favoriteRepositoryPort.getAllFavorites();
    }
}
