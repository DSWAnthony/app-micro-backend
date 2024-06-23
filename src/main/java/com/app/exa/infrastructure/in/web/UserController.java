package com.app.exa.infrastructure.in.web;

import com.app.exa.app.ports.in.CreateUserUseCase;
import com.app.exa.app.ports.in.DeleteUserByIdUseCase;
import com.app.exa.app.ports.in.GetAllUserUseCase;
import com.app.exa.app.ports.in.GetUserByIdUseCase;
import com.app.exa.domain.models.Respon;
import com.app.exa.domain.models.User;
import io.smallrye.mutiny.Uni;
import jakarta.annotation.security.RolesAllowed;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.eclipse.microprofile.graphql.GraphQLApi;
import org.eclipse.microprofile.graphql.Mutation;
import org.eclipse.microprofile.graphql.Query;
import java.util.*;

@ApplicationScoped
@GraphQLApi
public class UserController {
    @Inject
    CreateUserUseCase createUserUseCase;
    @Inject
    GetAllUserUseCase getAllUserUseCase;
    @Inject
    GetUserByIdUseCase getUserByIdUseCase;
    @Inject
    DeleteUserByIdUseCase deleteUserByIdUseCase;

    @RolesAllowed({"admin","user"})
    @Mutation("CreateUser")
    public Uni<User> createUser(User user){
        return createUserUseCase.createUser(user);
    }

    @RolesAllowed({"admin","user"})
    @Query("GetAllUsers")
    public Uni<List<User>> getAllUsers(){
        return getAllUserUseCase.getAllUser();
    }

    @RolesAllowed({"admin","user"})
    @Query("GetUserByID")
    public Uni<User> getUserById(Long id){
        return getUserByIdUseCase.getUserById(id);
    }

    @RolesAllowed("admin")
    @Mutation("DeleteUserById")
    public Uni<Respon> deleteUserById(Long id){
        return deleteUserByIdUseCase.deleteUserById(id);
    }
}
