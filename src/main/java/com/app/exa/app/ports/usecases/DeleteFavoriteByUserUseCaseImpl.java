package com.app.exa.app.ports.usecases;

import com.app.exa.app.ports.in.DeleteFavoriteByUserUseCase;
import com.app.exa.app.ports.out.FavoriteRepositoryPort;
import com.app.exa.domain.models.Respon;
import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class DeleteFavoriteByUserUseCaseImpl implements DeleteFavoriteByUserUseCase {
    @Inject
    FavoriteRepositoryPort favoriteRepositoryPort;

    @Override
    public Uni<Respon> deleteFavoriteByUser(Long book_id, Long user_id) {
        return favoriteRepositoryPort.deleteFavoriteByUser(book_id,user_id);
    }
}
