package com.app.exa.infrastructure.out.persistence.adapters;

import com.app.exa.app.ports.out.CategorieRepositoryPort;
import com.app.exa.domain.entities.CategoriesEntity;
import com.app.exa.domain.models.Categories;
import com.app.exa.domain.models.Respon;
import com.app.exa.infrastructure.out.persistence.mappers.CategorieMapper;
import com.app.exa.infrastructure.out.persistence.repositorys.CategorieRepository;
import io.quarkus.hibernate.reactive.panache.common.WithSession;
import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@ApplicationScoped
public class CategorieRepositoryAdapter implements CategorieRepositoryPort {
    @Inject
    CategorieRepository categorieRepository;

    @Transactional
    @WithSession
    public Uni<Categories> createCategorie(Categories categories) {
        CategoriesEntity categoriesEntity = CategorieMapper.domainToEntity(categories);
        return categorieRepository.persistAndFlush(categoriesEntity)
                .map(CategorieMapper::entityToDomain);
    }

    @WithSession
    @Transactional
    public Uni<Categories> updateCategorie(Categories categories) {
        return categorieRepository.findById(categories.getId())
                .onItem().ifNotNull().transformToUni(existingEntity -> {
                    existingEntity.setName(categories.getName());
                    return categorieRepository.persistAndFlush(existingEntity)
                            .map(CategorieMapper::entityToDomain);
                })
                .onItem().ifNull().failWith(new IllegalArgumentException("Category not found"));
    }

    @WithSession
    public Uni<List<Categories>> getAllCategories() {
        return categorieRepository.listAll()
                .onItem().transform(categoriesEntities -> categoriesEntities
                        .stream().map(CategorieMapper::entityToDomain)
                        .collect(Collectors.toList()));
    }

    @WithSession
    public Uni<Categories> getCategorieById(Long id) {
        return categorieRepository.findById(id)
                .map(CategorieMapper::entityToDomain);
    }

    @Transactional
    public Uni<Respon> deleteCategorieById(Long id) {
        return categorieRepository.deleteById(id)
                .map(deleted -> {
                    if (deleted) {
                        return new Respon("Ok", "Successfully");
                    } else {
                        return new Respon("Failed", "Error Id Not Exists");
                    }
                });
    }

}
