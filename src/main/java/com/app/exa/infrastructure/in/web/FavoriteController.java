package com.app.exa.infrastructure.in.web;

import com.app.exa.app.ports.in.*;
import com.app.exa.domain.models.Favorite;
import com.app.exa.domain.models.Respon;
import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.graphql.GraphQLApi;
import org.eclipse.microprofile.graphql.Mutation;
import org.eclipse.microprofile.graphql.Query;

import java.util.*;

@ApplicationScoped
@GraphQLApi
public class FavoriteController {
    @Inject
    CreateFavoriteUseCase createFavoriteUseCase;
    @Inject
    GetAllFavoriteUseCase getAllFavoriteUseCase;
    @Inject
    GetFavoriteByUserIdUseCase getFavoriteByUserIdUseCase;
    @Inject
    DeleteFavoriteByUserUseCase deleteFavoriteByUserUseCase;
    @Inject
    DeleteFavoriteByIdUseCase deleteFavoriteByIdUseCase;

    @Mutation("CreateFavorite")
    public Uni<Favorite> createFavorite(Long user_id ,Long book_id){
        return createFavoriteUseCase.createFavorite( user_id , book_id);
    }

    @Query("GetAllFavorite")
    public Uni<List<Favorite>> getAllFavorite(){
        return getAllFavoriteUseCase.getAllFavorite();
    }

    @Query("GetFavoriteByUserID")
    public Uni<List<Favorite>> getFavoriteByUserId(Long id){
        return getFavoriteByUserIdUseCase.getFavoriteByUserId(id);
    }

    @Mutation("DeleteFavoriteByUSer")
    public Uni<Respon> deleteFavorite(Long book_id, Long user_id){
        return deleteFavoriteByUserUseCase.deleteFavoriteByUser(book_id, user_id);
    }

    @Mutation("DeleteFavoriteById")
    public Uni<Respon> deleteFavoriteById(Long id){
        return deleteFavoriteByIdUseCase.deleFavoriteById(id);
    }
}
