package com.app.exa.app.ports.usecases;

import com.app.exa.app.ports.in.GetAllCategoriesUseCase;
import com.app.exa.app.ports.out.CategorieRepositoryPort;
import com.app.exa.domain.models.Categories;
import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.util.List;

@ApplicationScoped
public class GetAllCategoriesUseCaseImpl implements GetAllCategoriesUseCase {
    @Inject
    CategorieRepositoryPort categorieRepositoryPort;

    @Override
    public Uni<List<Categories>> getAllCategories() {
        return categorieRepositoryPort.getAllCategories();
    }
}
