package com.dh.usersservice.service;

import com.dh.usersservice.model.User;
import com.dh.usersservice.repository.IUserRepository;
import org.springframework.stereotype.Service;



@Service
public class UserService {
  private IUserRepository userRepository;

  public UserService(IUserRepository userRepository) {
    this.userRepository = userRepository;
  }

  public User updateColor(String id,String color){
    return userRepository.updateColor(id,color);
  }

  public User updateEmail(User user){
    return userRepository.save(user);
  }
}
