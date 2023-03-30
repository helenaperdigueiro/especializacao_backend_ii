package com.dh.usersservice.repository;

import com.dh.usersservice.model.User;
import lombok.RequiredArgsConstructor;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.resource.UserResource;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import javax.ws.rs.core.Response;

@Repository
@RequiredArgsConstructor
public class KeycloakRepository implements UserRepository{

    private final Keycloak keycloak;
    @Value("${dh.keycloak.realm}")
    private String realm;

    @Override
    public User getUserById(String id) {
        try {
            UserResource userResource = keycloak.realm(realm).users().get(id);
            UserRepresentation userRepresentation = userResource.toRepresentation();

            return fromRepresentation(userRepresentation);
        } catch (Exception e){
            System.out.println("Usuário não encontrado");
        }
        return null;
    }

    @Override
    public User saveUser(User user) {
        UserRepresentation userRepresentation = new UserRepresentation();
        userRepresentation.setFirstName(user.getFirstName());
        userRepresentation.setLastName(user.getLastName());
        userRepresentation.setEmail(user.getEmail());

        Response response = keycloak
                .realm(realm)
                .users()
                .create(userRepresentation);

        String userId = response.getLocation().getPath();



        UserRepresentation userRepresentation2 = keycloak
                .realm(realm)
                .users()
                .get(userId)
                .toRepresentation();

        return fromRepresentation(userRepresentation2);
    }

    private User fromRepresentation(UserRepresentation userRepresentation){
        return new User(userRepresentation.getId(), userRepresentation.getFirstName(), userRepresentation.getLastName(), userRepresentation.getEmail());
    }
}
