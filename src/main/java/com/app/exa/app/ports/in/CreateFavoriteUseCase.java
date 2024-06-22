package com.app.exa.app.ports.in;

import com.app.exa.domain.models.Favorite;
import io.smallrye.mutiny.Uni;

public interface CreateFavoriteUseCase {

    Uni<Favorite> createFavorite(Long user_id ,Long book_id);

}
