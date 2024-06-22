package com.app.exa.app.ports.out;

import com.app.exa.domain.models.Respon;
import com.app.exa.domain.models.User;
import io.smallrye.mutiny.Uni;

import java.util.List;

public interface UserRepositoryPort {

    Uni<List<User>> getAllUsers();
    Uni<User> createUser(User user);
    Uni<User> getUserById(Long id);
    Uni<Respon> deleteUserById(Long id);
}
