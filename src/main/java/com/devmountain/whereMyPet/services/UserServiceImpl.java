package com.devmountain.whereMyPet.services;

import com.devmountain.whereMyPet.dtos.UserDto;
import com.devmountain.whereMyPet.repositories.UserRepository;
import com.devmountain.whereMyPet.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder; //java bean from config class

    //handle registering user
    @Override
    @Transactional
    public List<String> addUser(UserDto userDto){
        List<String> response = new ArrayList<>();
        User user = new User(userDto);
        userRepository.saveAndFlush(user);
        response.add("User Added Successfully!");
        return response;
    }

    //handle login a User into our application
    @Override
    public List<String> userLogin(UserDto userDto){
        List<String> response = new ArrayList<>();
        Optional<User> userOptional = userRepository.findByUsername(userDto.getUsername());
        if(userOptional.isPresent()){
            if(passwordEncoder.matches(userDto.getPassword(), userOptional.get().getPassword())){
                response.add("User Login Successful");
                response.add(String.valueOf(userOptional.get().getId()));
            }else{
                response.add("Username or Password incorrect");
            }
        }else{
            response.add("User or password incorrect");
        }
        return response;
    }
}


//Service layer interacts with Repository layer. Spring handles the Dependency injection for us.
/*@Repository annotation are added to Repositories. Spring is able to find the corresponding dependency
and inject it where it is needed throughout the application by using the @Autowired annotation
*/

