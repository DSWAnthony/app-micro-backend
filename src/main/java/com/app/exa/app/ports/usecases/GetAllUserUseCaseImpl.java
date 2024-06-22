package com.app.exa.app.ports.usecases;

import com.app.exa.app.ports.in.GetAllUserUseCase;
import com.app.exa.app.ports.out.UserRepositoryPort;
import com.app.exa.domain.models.User;
import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.util.List;

@ApplicationScoped
public class GetAllUserUseCaseImpl implements GetAllUserUseCase {
    @Inject
    UserRepositoryPort userRepositoryPort;

    @Override
    public Uni<List<User>> getAllUser() {
        return userRepositoryPort.getAllUsers();
    }
}
