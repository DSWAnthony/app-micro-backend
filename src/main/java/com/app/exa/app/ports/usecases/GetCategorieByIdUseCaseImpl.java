package com.app.exa.app.ports.usecases;

import com.app.exa.app.ports.in.GetCategorieByIdUseCase;
import com.app.exa.app.ports.out.CategorieRepositoryPort;
import com.app.exa.domain.models.Categories;
import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class GetCategorieByIdUseCaseImpl implements GetCategorieByIdUseCase {
    @Inject
    CategorieRepositoryPort categorieRepositoryPort;

    @Override
    public Uni<Categories> getCategorieById(Long id) {
        return categorieRepositoryPort.getCategorieById(id);
    }
}
