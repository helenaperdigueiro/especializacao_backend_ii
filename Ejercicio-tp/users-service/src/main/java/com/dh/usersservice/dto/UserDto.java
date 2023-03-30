package com.dh.usersservice.dto;

import com.dh.usersservice.model.Bill;
import com.dh.usersservice.model.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class UserDto {

    private User user;

    private List<Bill> bills;
}
