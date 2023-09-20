package com.devmountain.whereMyPet.entities;

import com.devmountain.whereMyPet.dtos.FoundPetDto;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Date;


@Entity
@Table(name = "Found-Pet")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class FoundPet {
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long id;
   @Column
   private  String petType;
   @Column
    private String gender;

   @Column
    private String found;

   @Column
    private String contactEmail;

   @Column
   @JsonFormat(pattern="yyyy-MM-dd")
    private LocalDate date;

   @ManyToOne
   @JsonBackReference
    //create a private member variable named “user” of Type “User”
    private User user;

    //create a custom constructor inside the entity that accepts a DTO argument.
    public  FoundPet(FoundPetDto foundPetDto) {
        if(foundPetDto.getPetType() != null){
            this.petType = foundPetDto.getPetType();
        }
        if (foundPetDto.getGender() != null) {
            this.gender = foundPetDto.getGender();
        }
        if (foundPetDto.getFound() != null) {
            this.found = foundPetDto.getFound();
        }
        if (foundPetDto.getContactEmail() != null){
            this.contactEmail = foundPetDto.getContactEmail();
        }
        if(foundPetDto.getDate() != null){
            this.date = foundPetDto.getDate();
        }
    }
}
