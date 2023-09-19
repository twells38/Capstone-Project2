package com.devmountain.whereMyPet.repositories;

import com.devmountain.whereMyPet.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository  extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
}


/*The Repository layer is responsible for interacting with the database.
We can use Spring Data JPA however to make this process a little easier
as well as increase developer agility by leveraging the Domain Specific
Language provided by Spring Data JPA*/

/*
*@Repository annotation (this is what clues Spring Boot in to keep track of this resource for Dependency Injection)
* */

/*the interface extend “JpaRepository” which will accept two types within the carats,
<Type, ID_FIELD_TYPE> since we are in the UserRepository, the “Type”
or class we want JpaRepository to keep track of for us is the “User” class (the one you created within the “com.devmountain.noteApp.entities” package)
and its “id” field is of type “Long”.*/