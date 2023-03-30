package com.dh.usersservice.repository;

import com.dh.usersservice.model.User;

import java.util.Optional;

public interface IUserRepository {

  Optional<User> findById(String id);

  User updateColor(String id, String color);
}
