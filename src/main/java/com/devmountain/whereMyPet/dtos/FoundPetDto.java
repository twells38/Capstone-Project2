package com.devmountain.whereMyPet.dtos;

import com.devmountain.whereMyPet.entities.FoundPet;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FoundPetDto {
    private Long id;
    private String petType;
    private String gender;
    private String found;
    private String contactEmail;
    private LocalDate date;
    //private UserDto userDto;

    //create a custom constructor inside DTO that accepted an entity argument.
    public FoundPetDto(FoundPet foundPet){
        if(foundPet.getPetType() != null){
            this.petType = foundPet.getPetType();
        }
        if(foundPet.getId() != null){
            this.id = foundPet.getId();
        }
        if(foundPet.getGender() != null){
            this.gender = foundPet.getGender();
        }
        if(foundPet.getFound() != null){
            this.found = foundPet.getFound();
        }
        if(foundPet.getContactEmail() != null){
            this.contactEmail = foundPet.getContactEmail();
        }
        if(foundPet.getDate() != null){
            this.date = foundPet.getDate();
        }
    }
}
