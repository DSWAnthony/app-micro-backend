package com.app.exa.app.ports.usecases;

import com.app.exa.app.ports.in.CreateUserUseCase;
import com.app.exa.app.ports.out.UserRepositoryPort;
import com.app.exa.domain.models.User;
import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class CreateUserUseCaseImpl implements CreateUserUseCase {
    @Inject
    UserRepositoryPort userRepositoryPort;

    @Override
    public Uni<User> createUser(User user) {
        return userRepositoryPort.createUser(user);
    }
}
