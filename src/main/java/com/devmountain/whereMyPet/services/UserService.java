package com.devmountain.whereMyPet.services;

import com.devmountain.whereMyPet.dtos.UserDto;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface UserService {
    //handle registering user
    @Transactional
    List<String> addUser(UserDto userDto);

    //handle login a User into our application
    List<String> userLogin(UserDto userDto);
}
