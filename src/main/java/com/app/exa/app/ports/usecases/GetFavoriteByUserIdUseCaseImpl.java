package com.app.exa.app.ports.usecases;

import com.app.exa.app.ports.in.GetFavoriteByUserIdUseCase;
import com.app.exa.app.ports.out.FavoriteRepositoryPort;
import com.app.exa.domain.models.Favorite;
import io.smallrye.mutiny.Uni;
import java.util.*;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class GetFavoriteByUserIdUseCaseImpl implements GetFavoriteByUserIdUseCase {
    @Inject
    FavoriteRepositoryPort favoriteRepositoryPort;

    @Override
    public Uni<List<Favorite>> getFavoriteByUserId(Long id) {
        return favoriteRepositoryPort.getFavoriteByUserId(id);
    }
}
