package com.devmountain.whereMyPet.entities;

import com.devmountain.whereMyPet.dtos.LostPetDto;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Date;

@Entity
@Table(name = "Lost-Pet")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class LostPet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String petType;
    @Column
    private String petName;

    @Column
    private String gender;

    @Column
    private String lastSeen;

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
    public LostPet(LostPetDto lostPetDto){
        if(lostPetDto.getPetType() != null){
            this.petType = lostPetDto.getPetType();
        }
        if(lostPetDto.getPetName() != null){
            this.petName = lostPetDto.getPetName();
        }
        if(lostPetDto.getGender() != null){
            this.gender = lostPetDto.getGender();
        }
        if(lostPetDto.getLastSeen() != null){
            this.lastSeen = lostPetDto.getLastSeen();
        }
        if(lostPetDto.getContactEmail() != null){
            this.contactEmail = lostPetDto.getContactEmail();
        }
        if(lostPetDto.getDate() != null){
            this.date = lostPetDto.getDate();
        }
    }
}
