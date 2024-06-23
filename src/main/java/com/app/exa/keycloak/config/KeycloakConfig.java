package com.app.exa.keycloak.config;

import jakarta.enterprise.context.ApplicationScoped;

import org.jboss.resteasy.client.jaxrs.internal.ResteasyClientBuilderImpl;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.KeycloakBuilder;
import org.keycloak.admin.client.resource.RealmResource;
import org.keycloak.admin.client.resource.UsersResource;

@ApplicationScoped
public class KeycloakConfig {


   private static final String server_url = "http://localhost:8380";
   private static final String real_name = "quarkus-realm-dev";
   private static final String real_master = "master";
   private static final String admin_cli = "admin-cli";
   private static final String user_console = "admin";
   private static final String pass_console = "gamer939428";
   private static final String adminClientSecret = "sVLZ5fC0Wo5V5KoA4eg3LNjxdRbjGmwG";

    public static RealmResource getRealResource() {
        try {
            Keycloak keycloak = KeycloakBuilder.builder()
                    .serverUrl(server_url)
                    .realm(real_master)
                    .clientId(admin_cli)
                    .username(user_console)
                    .password(pass_console)
                    .clientSecret(adminClientSecret)
                    .resteasyClient(new ResteasyClientBuilderImpl().connectionPoolSize(10).build())
                    .build();

            return keycloak.realm(real_name);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static UsersResource getUserResources(){
        RealmResource realmResource = getRealResource();
        return realmResource.users();
    }
}

