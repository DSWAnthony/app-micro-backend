package com.app.exa.app.ports.in;

import com.app.exa.domain.models.Respon;
import io.smallrye.mutiny.Uni;

public interface DeleteFavoriteByUserUseCase {
    Uni<Respon> deleteFavoriteByUser(Long book_id, Long user_id);
}
