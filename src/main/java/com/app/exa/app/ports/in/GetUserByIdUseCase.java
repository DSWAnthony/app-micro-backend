package com.app.exa.app.ports.in;

import com.app.exa.domain.models.User;
import io.smallrye.mutiny.Uni;

public interface GetUserByIdUseCase {
    Uni<User> getUserById(Long id);
}
