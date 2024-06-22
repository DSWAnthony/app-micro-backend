package com.app.exa.app.ports.usecases;

import com.app.exa.app.ports.in.UpdateCategorieByIdUseCase;
import com.app.exa.app.ports.out.CategorieRepositoryPort;
import com.app.exa.domain.models.Categories;
import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class UpdateCategorieByIdUseCaseImpl implements UpdateCategorieByIdUseCase {
    @Inject
    CategorieRepositoryPort categorieRepositoryPort;

    @Override
    public Uni<Categories> updateCategorie(Categories categories) {
        return categorieRepositoryPort.updateCategorie(categories);
    }
}
