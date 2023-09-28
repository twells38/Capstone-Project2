package com.devmountain.whereMyPet.controllers;

import com.devmountain.whereMyPet.dtos.FoundPetDto;
import com.devmountain.whereMyPet.services.FoundPetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/v1/foundPets")
public class FoundPetController {
      @Autowired
      private FoundPetService foundPetService;

      //create a method to add a found pet
    @PostMapping("/user/{userId}")
    public void addFoundPet(@RequestBody FoundPetDto foundPetDto,@PathVariable Long userId){
        foundPetService.addFoundPet(foundPetDto,userId);
    }

    //create a method to delete found pet by id
    @DeleteMapping("/{foundPetId}")
    public void deleteFoundDogById(@PathVariable Long foundPetId){
        foundPetService.deleteFoundPetById(foundPetId);
    }

    //create a method to update a found pet
    @PutMapping("/{foundPetId}")
    public void updateFoundPet(@PathVariable Long foundPetId, @RequestBody FoundPetDto foundPetDto){
        foundPetService.updateFoundPetById(foundPetDto, foundPetId);
    }

    //create a method to retrieve all found pets by user
    @GetMapping("/user/{userId}")
    public List<FoundPetDto> getAllFoundPetByUser(@PathVariable Long userId){
        return foundPetService.getAllFoundPetByUserId(userId);
    }

    //create a method to retrieve a found pet by found pet's id
    @GetMapping("/{foundPetId}")
    public Optional<FoundPetDto> fetFoundPetById(@PathVariable Long foundPetId){
        return foundPetService.getFoundPetById(foundPetId);
    }

    //create a method to retrieve all found pets
    @GetMapping("/allFoundPets")
    public List<FoundPetDto> getAllFoundPets(){
        return foundPetService.getAllFoundPets();
    }
}
