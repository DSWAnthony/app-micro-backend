package com.app.exa.keycloak.service;

import com.app.exa.domain.models.User;
import com.app.exa.keycloak.config.KeycloakConfig;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.ws.rs.core.Response;
import org.keycloak.OAuth2Constants;
import org.keycloak.admin.client.resource.RealmResource;
import org.keycloak.admin.client.resource.UsersResource;
import org.keycloak.representations.idm.CredentialRepresentation;
import org.keycloak.representations.idm.RoleRepresentation;
import org.keycloak.representations.idm.UserRepresentation;

import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class KeycloakUserServiceImpl implements KeycloakUserService {

    @Override
    public User createUser(User userRegistrationRecord) {
        UserRepresentation userRepresentation = getUserRepresentation(userRegistrationRecord);
        UsersResource usersResource = KeycloakConfig.getUserResources();
        try (Response response = usersResource.create(userRepresentation)) {
            System.out.println("Status " + response.getStatus());

            if (response.getStatus() == 201) {
                // Usuario creado exitosamente
                List<UserRepresentation> representationList = usersResource.search(userRepresentation.getUsername(), true);
                if (representationList != null && !representationList.isEmpty()) {
                    UserRepresentation createdUser = representationList.get(0); // Suponiendo que haya solo uno con ese username

                    RealmResource realmResource = KeycloakConfig.getRealResource();
                    List<RoleRepresentation> representations = List.of(realmResource.roles().get("user").toRepresentation());
                    realmResource.users().get(createdUser.getId()).roles().realmLevel().add(representations);

                    if (!createdUser.isEmailVerified()) {
                        emailVerification(createdUser.getId());
                    }
                }
                return userRegistrationRecord;
            } else {
                System.out.println("Failed to create user. Status: " + response.getStatus());
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private static UserRepresentation getUserRepresentation(User userRegistrationRecord) {
        UserRepresentation userRepresentation1 = new UserRepresentation();
        userRepresentation1.setFirstName(userRegistrationRecord.getNames());
        userRepresentation1.setLastName(userRegistrationRecord.getLastName());
        userRepresentation1.setEmail(userRegistrationRecord.getEmail());
        userRepresentation1.setUsername(userRegistrationRecord.getUser());
        userRepresentation1.setEnabled(true);
        userRepresentation1.setEmailVerified(true);

        CredentialRepresentation credentialRepresentation=new CredentialRepresentation();
        credentialRepresentation.setTemporary(false);
        credentialRepresentation.setType(OAuth2Constants.PASSWORD);
        credentialRepresentation.setValue(userRegistrationRecord.getPassword());

        List<CredentialRepresentation> list = new ArrayList<>();
        list.add(credentialRepresentation);
        userRepresentation1.setCredentials(list);


        return userRepresentation1;
    }


    @Override
    public void emailVerification(String userId) {
        UsersResource usersResource = KeycloakConfig.getUserResources();
        usersResource.get(userId).sendVerifyEmail();
    }
}
