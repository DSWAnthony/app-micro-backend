package com.app.exa.app.ports.out;

import com.app.exa.domain.models.Favorite;
import com.app.exa.domain.models.Respon;
import io.smallrye.mutiny.Uni;

import java.util.*;

public interface FavoriteRepositoryPort {

    Uni<Favorite> createFavorite(Long user_id ,Long book_id);
    Uni<List<Favorite>> getAllFavorites();
    Uni<List<Favorite>> getFavoriteByUserId(Long id);
    Uni<Respon> deleteFavoriteByUser(Long book_id, Long user_id);
    Uni<Respon> deleFavoriteById(Long id);
}
