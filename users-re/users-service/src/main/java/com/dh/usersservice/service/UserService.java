package com.dh.usersservice.service;

import com.dh.usersservice.model.User;
import com.dh.usersservice.repository.IUserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class UserService {
  private IUserRepository userRepository;

  public UserService(IUserRepository userRepository) {
    this.userRepository = userRepository;
  }

  public Optional<User> findById(String id) {
    return userRepository.findById(id);
  }

  public User updateColor(String id,String color){
    return userRepository.updateColor(id,color);
  }
}
