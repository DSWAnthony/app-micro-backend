package com.app.exa.infrastructure.out.persistence.mappers;

import com.app.exa.domain.entities.UserEntity;
import com.app.exa.domain.models.User;

public class UserMapper {

    public static User entityToDomain(UserEntity userEntity) {
        User user = new User();
        user.setId(userEntity.getId());
        user.setNames(userEntity.getNames());
        user.setEmail(userEntity.getEmail());
        user.setPassword(userEntity.getPassword());
        user.setUser(userEntity.getUser());
        user.setLastName(userEntity.getLastName());
        if (userEntity.getFavorites() != null){
            user.setFavorites(userEntity.getFavorites().stream().map(FavoriteMapper::entityToDomain).toList());
        }
        return user;
    }

    public static UserEntity domainToEntity(User user) {
        UserEntity userEntity = new UserEntity();
        userEntity.setId(user.getId());
        userEntity.setNames(user.getNames());
        userEntity.setEmail(user.getEmail());
        userEntity.setUser(user.getUser());
        userEntity.setLastName(user.getLastName());
        userEntity.setPassword(user.getPassword());
        return userEntity;
    }
}
