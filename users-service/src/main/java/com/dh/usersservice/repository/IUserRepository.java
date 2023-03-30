package com.dh.usersservice.repository;

import com.dh.usersservice.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IUserRepository extends JpaRepository<User, String> {

  User updateColor(String id, String color);
}
