package com.dh.usersservice.service;

import com.dh.usersservice.dto.UserDto;
import com.dh.usersservice.model.User;
import com.dh.usersservice.repository.BillRepository;
import com.dh.usersservice.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    private final BillRepository billRepository;

    public UserDto findUserById(String id){
        if(userRepository.getUserById(id) != null){
            return new UserDto(userRepository.getUserById(id), billRepository.getBillsByUserId(id));
        }
        return null;
    }

    public User saveUser(User user) {
        return userRepository.saveUser(user);
    }
}
