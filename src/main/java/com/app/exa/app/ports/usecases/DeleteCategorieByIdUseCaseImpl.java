package com.app.exa.app.ports.usecases;

import com.app.exa.app.ports.in.DeleteCategorieByIdUseCase;
import com.app.exa.app.ports.out.CategorieRepositoryPort;
import com.app.exa.domain.models.Respon;
import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class DeleteCategorieByIdUseCaseImpl implements DeleteCategorieByIdUseCase {
    @Inject
    CategorieRepositoryPort categorieRepositoryPort;

    @Override
    public Uni<Respon> deleteCategorieById(Long id) {
        return categorieRepositoryPort.deleteCategorieById(id);
    }
}
