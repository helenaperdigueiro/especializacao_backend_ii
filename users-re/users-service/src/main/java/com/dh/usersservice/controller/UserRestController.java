package com.dh.usersservice.controller;

import com.dh.usersservice.model.User;

import com.dh.usersservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;


@RestController
@RequestMapping
public class UserRestController {

  @Autowired
  private UserService userService;

  @GetMapping
  public Optional<User> findById(@RequestParam String id) {
    return userService.findById(id);
  }

  @GetMapping("/users")
  public User getAll(@RequestParam  String id, @RequestParam String color ){
    return userService.updateColor(id,color);
  }
}
