package com.devmountain.whereMyPet.controllers;

import com.devmountain.whereMyPet.dtos.UserDto;
import com.devmountain.whereMyPet.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {
    // Autowired in our Dependencies, which are the UserService because Controllers interact with ServiceLayers and the PasswordEncoder so that we can have incoming passwords
    @Autowired
    private UserService userService;
    @Autowired
    private PasswordEncoder passwordEncoder;

    //create a method that handle POST request to be able to register a USER.
    @PostMapping("/register")
    public List<String> addUser(@RequestBody UserDto userDto){
        String passHash = passwordEncoder.encode(userDto.getPassword());
        userDto.setPassword(passHash);
       return userService.addUser(userDto);
    }
     //create a method that handle logging a user in.
    @PostMapping("/login")
    public List<String> userLogin(@RequestBody UserDto userDto){
        return userService.userLogin(userDto);
    }


}







/*The controller layer is where to handle incoming HTTP requests and defines the application behavior.*/
/*In Spring boot , there are 2 primary annotations for creating controller : @RestController, @Controller*/
/* @RestController Annotation
*  It is primarily used for building RESTful web service that return data in a format other that HTML, typically JSON or XML.
*  Controllers annotated with @RestController do not return views, instead, they return the data directly whhich is then serialized into the chosen format and sent as the HTTP response body.
*/

/* @Controller Annotation
* The @Controller annotation is the older of the two and is part of the Spring MVC framework
* It is typically used for building web application that render views or HTML pages
* Controllers annotated with @Controller are responsible for handling HTTP requests, processing data, and returning a view to be rendered by the clients browser.
* The method within a @Controller class often return a logical view name, which Spring Boot resolves to an HTML template.*/

/*The key different
* Response Type: @Controller returns views(HTML pages) , @RestController returns data(JSON, XML)
* Response Handling: @Controller relies on ViewResolvers to render views, @RestController directly serializes to render views.
* Convenience : @RestController is more convenient for building RESTful API as it eliminates the need to annotate every method with @ResponseBody.
* Usage Scenario: USe @Controller when building traditional web applications with views, Use @RestController when developing RESTful web services that serve data to clients.*/