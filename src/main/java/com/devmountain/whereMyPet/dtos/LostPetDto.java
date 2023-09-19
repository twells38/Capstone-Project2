package com.devmountain.whereMyPet.dtos;


import com.devmountain.whereMyPet.entities.LostPet;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.sql.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LostPetDto implements Serializable {
    private Long id;
    private String petType;
    private String petName;
    private String gender;
    private String lastSeen;
    private String contactEmail;
    private Date date;
    private UserDto userDto;

    //create a custom constructor inside DTO that accepted an entity argument.
    public LostPetDto(LostPet lostPet){
        if(lostPet.getPetType() != null){
            this.petType = lostPet.getPetType();
        }
        if(lostPet.getId() != null){
            this.id = lostPet.getId();
        }
        if(lostPet.getPetName() != null){
            this.petName = lostPet.getPetName();
        }
        if(lostPet.getGender() != null){
            this.lastSeen = lostPet.getLastSeen();
        }
        if(lostPet.getContactEmail() != null){
            this.contactEmail = lostPet.getContactEmail();
        }
        if(lostPet.getDate() != null){
            this.date = lostPet.getDate();
        }
    }

}
