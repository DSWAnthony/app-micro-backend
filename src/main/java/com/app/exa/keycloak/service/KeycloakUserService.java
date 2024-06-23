package com.app.exa.keycloak.service;

import com.app.exa.domain.models.User;

public interface KeycloakUserService {

    User createUser(User userRegistrationRecord);
    void emailVerification(String userId);
}
