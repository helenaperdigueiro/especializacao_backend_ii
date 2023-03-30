package com.dh.usersservice.controller;

import com.dh.usersservice.model.User;

import com.dh.usersservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping
public class UserRestController {
  @Autowired
  private UserService userService;

  @GetMapping("/users/{id}/{color}")
  public User getAll(@PathVariable String id, @PathVariable String color){
    return userService.updateColor(id,color);
  }

  @PutMapping("/users/update")
  public User updateUser(@RequestBody User user){
    return userService.updateEmail(user);
  }
}
