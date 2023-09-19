package com.devmountain.whereMyPet.services;

import com.devmountain.whereMyPet.dtos.LostPetDto;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface LostPetService {
    //create method addLostPet
    @Transactional
    void addLostPet(LostPetDto lostPetDto, Long userId);

    //create method to delete lostPet
    @Transactional
    void deleteLostPetById(Long lostPetId);

    //create method to update lostPet
    @Transactional
    void updateLostPetById(LostPetDto lostPetDto);

    //create a method to find all lost pet by user
    List<LostPetDto> getAllLostPetByUserId(Long userId);

    //create a method to find a lost pet by lost pet's id
    Optional<LostPetDto> getLostPetById(Long lostPetId);
}
