package com.app.exa.app.ports.in;

import com.app.exa.domain.models.User;
import io.smallrye.mutiny.Uni;

import java.util.List;

public interface GetAllUserUseCase {

    Uni<List<User>> getAllUser();
}
