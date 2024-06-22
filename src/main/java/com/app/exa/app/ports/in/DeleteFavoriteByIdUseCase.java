package com.app.exa.app.ports.in;

import com.app.exa.domain.models.Respon;
import io.smallrye.mutiny.Uni;


public interface DeleteFavoriteByIdUseCase {
    Uni<Respon> deleFavoriteById(Long id);
}
