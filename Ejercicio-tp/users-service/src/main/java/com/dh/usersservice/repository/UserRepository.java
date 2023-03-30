package com.dh.usersservice.repository;

import com.dh.usersservice.model.User;

public interface UserRepository {

    User getUserById(String id);
    User saveUser(User user);
}
