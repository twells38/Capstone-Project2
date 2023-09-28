package com.devmountain.whereMyPet.services;

import com.devmountain.whereMyPet.dtos.FoundPetDto;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface FoundPetService {
    //create a method to add a found pet
    @Transactional
    void addFoundPet(FoundPetDto foundPetDto, Long userId);

    //create a method to delete a found pet
    @Transactional
    void deleteFoundPetById(Long foundPetId);

    //create a method to update a found pet
    @Transactional
    void updateFoundPetById(FoundPetDto foundPetDto, Long foundPetId);

    //create a method that find all found pet by a user
    List<FoundPetDto> getAllFoundPetByUserId(Long userId);

    //create a method that find a found pet by found pet's id.
    Optional<FoundPetDto> getFoundPetById(Long foundPetId);

    //create a method that retrieve all found pets
    List<FoundPetDto> getAllFoundPets();
}
