package com.devmountain.whereMyPet.controllers;

import com.devmountain.whereMyPet.dtos.LostPetDto;
import com.devmountain.whereMyPet.services.LostPetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/v1/lostPets")
public class LostPetController {
    @Autowired
    private LostPetService lostPetService;

    //create method to add a new lost pet by user
    @PostMapping("/user/{userId}")
    public void addLostPet(@RequestBody LostPetDto lostPetDto, @PathVariable Long userId){
        lostPetService.addLostPet(lostPetDto,userId);
    }

    //create a method to update an existing lost pet
    @PutMapping("/{lostPetId}")
    public void updateLostPet(@PathVariable Long lostPetId ,@RequestBody LostPetDto lostPetDto){
        lostPetService.updateLostPetById(lostPetDto, lostPetId); //invoke updateLostPetById method from lostPetService interface
    }

    //create a method to delete an existing lost pet
    @DeleteMapping("/{lostPetId}")
    public void deleteLostPet(@PathVariable Long lostPetId){
        lostPetService.deleteLostPetById(lostPetId);
    }

    //create a method to retrieve a lost pet by lost pet 's id.
    @GetMapping("/{lostPetId}")
    public Optional<LostPetDto> getLostPetById(@PathVariable Long lostPetId){
        return lostPetService.getLostPetById(lostPetId);
    }

    //create method to retrieve all lost pet by user
    //@PathVariable is used to capture values from the URL path
    @GetMapping("/user/{userId}")
    public List<LostPetDto> getAllLostPetByUser(@PathVariable Long userId){
      return lostPetService.getAllLostPetByUserId(userId);
    }

    //create method to retrieve all lost pets
    @GetMapping("/allLostPets")
    public List<LostPetDto> getAllLostPets(){
        return lostPetService.getAllLostPets();
    }

}
