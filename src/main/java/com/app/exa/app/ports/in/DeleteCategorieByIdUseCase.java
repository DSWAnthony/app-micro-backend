package com.app.exa.app.ports.in;

import com.app.exa.domain.models.Respon;
import io.smallrye.mutiny.Uni;

public interface DeleteCategorieByIdUseCase {
    Uni<Respon> deleteCategorieById(Long id);
}
