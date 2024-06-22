package com.app.exa.app.ports.usecases;

import com.app.exa.app.ports.in.DeleteFavoriteByIdUseCase;
import com.app.exa.app.ports.out.FavoriteRepositoryPort;
import com.app.exa.domain.models.Respon;
import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class DeleteFavoriteByIdUseCaseImpl implements DeleteFavoriteByIdUseCase {
    @Inject
    FavoriteRepositoryPort favoriteRepositoryPort;

    @Override
    public Uni<Respon> deleFavoriteById(Long id) {
        return favoriteRepositoryPort.deleFavoriteById(id);
    }
}
