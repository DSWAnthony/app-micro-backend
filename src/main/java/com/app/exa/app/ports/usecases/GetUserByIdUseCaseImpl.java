package com.app.exa.app.ports.usecases;

import com.app.exa.app.ports.in.GetUserByIdUseCase;
import com.app.exa.app.ports.out.UserRepositoryPort;
import com.app.exa.domain.models.User;
import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class GetUserByIdUseCaseImpl implements GetUserByIdUseCase {
    @Inject
    UserRepositoryPort userRepositoryPort;

    @Override
    public Uni<User> getUserById(Long id) {
        return userRepositoryPort.getUserById(id);
    }
}
