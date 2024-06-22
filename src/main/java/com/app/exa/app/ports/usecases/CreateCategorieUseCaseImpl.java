package com.app.exa.app.ports.usecases;

import com.app.exa.app.ports.in.CreateCategorieUseCase;
import com.app.exa.app.ports.out.CategorieRepositoryPort;
import com.app.exa.domain.models.Categories;
import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class CreateCategorieUseCaseImpl implements CreateCategorieUseCase {
    @Inject
    CategorieRepositoryPort categorieRepositoryPort;

    @Override
    public Uni<Categories> createCategorie(Categories categories) {
        return categorieRepositoryPort.createCategorie(categories);
    }
}
