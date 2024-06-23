package com.app.exa.infrastructure.out.persistence.adapters;

import com.app.exa.app.ports.out.UserRepositoryPort;
import com.app.exa.domain.entities.UserEntity;
import com.app.exa.domain.models.Respon;
import com.app.exa.domain.models.User;
import com.app.exa.infrastructure.out.persistence.mappers.UserMapper;
import com.app.exa.infrastructure.out.persistence.repositorys.UserRepository;
import com.app.exa.keycloak.service.KeycloakUserService;
import io.quarkus.hibernate.reactive.panache.common.WithSession;
import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@ApplicationScoped
public class UserRepositoryAdapter implements UserRepositoryPort {
    @Inject
    KeycloakUserService keycloakServices;
    @Inject
    UserRepository userRepository;

    @WithSession
    public Uni<List<User>> getAllUsers() {
        return userRepository.listAll()
                .onItem().transform(userEntities -> userEntities
                        .stream().map(UserMapper::entityToDomain)
                        .collect(Collectors.toList()));
    }

    @Transactional
    @WithSession
    public Uni<User> createUser(User user) {
        UserEntity userEntity = UserMapper.domainToEntity(user);
        keycloakServices.createUser(user);
        return userRepository.persistAndFlush(userEntity)
                .map(UserMapper::entityToDomain);
    }

    @WithSession
    public Uni<User> getUserById(Long id) {
        return userRepository.findById(id)
                .map(UserMapper::entityToDomain);
    }

    @Override
    public Uni<Respon> deleteUserById(Long id) {
        return userRepository.deleteById(id)
                .map(deleted ->{
                    if (deleted){
                        return new Respon("OK","Delete Successfully");
                    }else {
                        return new Respon("ERROR","Delete Filed");
                    }
                });
    }
}
