package com.app.exa.infrastructure.in.web;

import com.app.exa.app.ports.in.*;
import com.app.exa.domain.models.Categories;
import com.app.exa.domain.models.Respon;
import io.smallrye.mutiny.Uni;
import java.util.*;

import jakarta.annotation.security.RolesAllowed;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.eclipse.microprofile.graphql.GraphQLApi;
import org.eclipse.microprofile.graphql.Mutation;
import org.eclipse.microprofile.graphql.Query;

@ApplicationScoped
@GraphQLApi
public class CategorieController {
    @Inject
    CreateCategorieUseCase createCategorieUseCase;
    @Inject
    GetAllCategoriesUseCase getAllCategoriesUseCase;
    @Inject
    GetCategorieByIdUseCase getCategorieByIdUseCase;
    @Inject
    DeleteCategorieByIdUseCase deleteCategorieByIdUseCase;
    @Inject
    UpdateCategorieByIdUseCase updateCategorieByIdUseCase;

    @RolesAllowed({"admin","user"})
    @Query("GetAllCategories")
    public Uni<List<Categories>> getAllCategories(){
        return getAllCategoriesUseCase.getAllCategories();
    }

    @RolesAllowed({"admin","user"})
    @Query("GetCategorieById")
    public Uni<Categories> getCategorieById(Long id){
        return getCategorieByIdUseCase.getCategorieById(id);
    }

    @RolesAllowed("admin")
    @Mutation("CreateCategorie")
    public Uni<Categories> createCategorie(Categories categories){
        return createCategorieUseCase.createCategorie(categories);
    }

    @RolesAllowed("admin")
    @Mutation("DeleteCategorieById")
    public Uni<Respon> deletCategoriById(Long id){
        return deleteCategorieByIdUseCase.deleteCategorieById(id);
    }

    @RolesAllowed("admin")
    @Mutation("UpdateCategorieById")
    public Uni<Categories> updateCategorieById(Categories categories){
        return updateCategorieByIdUseCase.updateCategorie(categories);
    }
}
