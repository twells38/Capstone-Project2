package com.devmountain.whereMyPet.entities;

import com.devmountain.whereMyPet.dtos.UserDto;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "Users")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //create a new private member variable called username
    @Column(unique = true)
    private String username;

    @Column
    private String password;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY, cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JsonBackReference
    private Set<LostPet> lostPetSet = new HashSet<>();

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY, cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JsonBackReference
    private Set<FoundPet> foundPetSet = new HashSet<>();

    //create a custom constructor inside the entity that accepts a DTO argument.
    public User(UserDto userDto){
        if(userDto.getUsername() != null){
            this.username = userDto.getUsername();
        }
        if(userDto.getPassword() != null){
            this.password = userDto.getPassword();
        }
    }
}






/*Private member variables are only accessible within the class they reside in,
so for us to be able to get these values or set these values, we need to generate getters and setters for each field.*/

/*Hibernate is what’s called an Object Relational Mapper or ORM.
Hibernate takes your Plain Old Java Objects and maps the member fields to a Relational Database for you.
It enables you to be able to persist all the incoming data without having to write SQL queries. Let’s be clear though,
just because you don’t have to write SQL queries does not mean that you can’t write SQL queries.
You can still create your custom queries and use them.*/
/*Hibernate also keeps track of our relationships within the relational database (e.g. One-to-One, One-to-Many, Many-to-One,
and Many-to-Many relationships) and build those relationships between your objects.*/

/*In conjunction with Hibernate we are going to be using Spring Data JPA which is an amazing tool that handles the Repository layer implementations for us in our development based on our Interfaces that we create.*/