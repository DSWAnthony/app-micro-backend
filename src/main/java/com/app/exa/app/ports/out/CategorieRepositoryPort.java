package com.app.exa.app.ports.out;

import com.app.exa.domain.models.Categories;
import com.app.exa.domain.models.Respon;
import io.smallrye.mutiny.Uni;

import java.util.List;

public interface CategorieRepositoryPort {

    Uni<Categories> createCategorie(Categories categories);
    Uni<List<Categories>> getAllCategories();
    Uni<Categories> getCategorieById(Long id);
    Uni<Respon> deleteCategorieById(Long id);
    Uni<Categories> updateCategorie(Categories categories);
}
