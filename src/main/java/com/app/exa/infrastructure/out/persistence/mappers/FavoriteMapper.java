package com.app.exa.infrastructure.out.persistence.mappers;

import com.app.exa.domain.entities.FavoriteEntity;
import com.app.exa.domain.models.Favorite;
import com.app.exa.domain.models.User;

import java.time.LocalDateTime;

public class FavoriteMapper {

    public static Favorite entityToDomain(FavoriteEntity favoriteEntity){
        Favorite favorite = new Favorite();
        favorite.setId(favoriteEntity.getId());
        favorite.setDate(LocalDateTime.now());
        User user = new User();
        if (favoriteEntity.getUserEntity()!=null) {
            user.setId(favoriteEntity.getUserEntity().getId());
            user.setNames(favoriteEntity.getUserEntity().getNames());
            user.setEmail(favoriteEntity.getUserEntity().getEmail());
            user.setPassword(favoriteEntity.getUserEntity().getPassword());
        }
        favorite.setUser(user);
        favorite.setBook(BookMapper.entityToDomain(favoriteEntity.getBookEntity()));
        return favorite;
    }

    public static FavoriteEntity domainToEntity(Favorite favorite) {
        FavoriteEntity favoriteEntity = new FavoriteEntity();
        favoriteEntity.setId(favorite.getId());
        favoriteEntity.setDate(LocalDateTime.now());
        return favoriteEntity;
    }
}