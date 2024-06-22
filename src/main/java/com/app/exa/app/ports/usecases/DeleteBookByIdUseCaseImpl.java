package com.app.exa.app.ports.usecases;

import com.app.exa.app.ports.in.DeleteBookByIdUseCase;
import com.app.exa.app.ports.out.BookRepositoryPort;
import com.app.exa.domain.models.Respon;
import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class DeleteBookByIdUseCaseImpl implements DeleteBookByIdUseCase {
    @Inject
    BookRepositoryPort bookRepositoryPort;

    @Override
    public Uni<Respon> deleteBookById(Long id) {
        return bookRepositoryPort.deleteBookById(id);
    }
}
