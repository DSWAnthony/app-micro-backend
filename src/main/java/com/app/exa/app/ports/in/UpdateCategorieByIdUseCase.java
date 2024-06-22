package com.app.exa.app.ports.in;

import com.app.exa.domain.models.Categories;
import io.smallrye.mutiny.Uni;

public interface UpdateCategorieByIdUseCase {
    Uni<Categories> updateCategorie(Categories categories);
}
