package com.app.exa.app.ports.usecases;

import com.app.exa.app.ports.in.DeleteUserByIdUseCase;
import com.app.exa.app.ports.out.UserRepositoryPort;
import com.app.exa.domain.models.Respon;
import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class DeleteUserByIdUseCaseImpl implements DeleteUserByIdUseCase {
    @Inject
    UserRepositoryPort userRepositoryPort;

    @Override
    public Uni<Respon> deleteUserById(Long id) {
        return userRepositoryPort.deleteUserById(id);
    }
}
