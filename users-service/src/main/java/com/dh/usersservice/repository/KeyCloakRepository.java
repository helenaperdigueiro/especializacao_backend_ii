package com.dh.usersservice.repository;

import com.dh.usersservice.model.User;

import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.resource.UserResource;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class KeyCloakRepository {
  @Autowired
  private Keycloak keycloak;
  @Value("${dh.keycloak.realm}")
  private String realm;

  @Autowired
  private IUserRepository iUserRepository;

  public User updateColor(String id, String color) {
    UserResource userResource = keycloak
        .realm(realm)
        .users().get(id);

    UserRepresentation userRepresentation = userResource.toRepresentation();

    Map<String, List<String>> attributes = new HashMap<>();
    attributes.put("color", List.of(color));
    userRepresentation.setAttributes(attributes);

    userResource.update(userRepresentation);
    return fromRepresentation(userRepresentation);
  }

  private User fromRepresentation(UserRepresentation userRepresentation) {
    return new User(userRepresentation.getId(), userRepresentation.getFirstName(), userRepresentation.getLastName(), userRepresentation.getEmail(),userRepresentation.getAttributes().get("color").get(0));
  }


}
