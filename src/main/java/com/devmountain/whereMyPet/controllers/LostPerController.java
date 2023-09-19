package com.devmountain.whereMyPet.controllers;

import com.devmountain.whereMyPet.dtos.LostPetDto;
import com.devmountain.whereMyPet.services.LostPetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/lostPets")
public class LostPerController {
    @Autowired
    private LostPetService lostPetService;

    //create method to add a new lost pet by user
    @PostMapping("/user/{userId}")
    public void addLostPet(@RequestBody LostPetDto lostPetDto, @PathVariable Long userId){
        lostPetService.addLostPet(lostPetDto,userId);
    }

    //create a method to update an existing lost pet



    //create method to retrieve all lost pet by
    //@PathVariable is used to capture values from the URL path
    @GetMapping("/user/{userId}")
    public List<LostPetDto> lostPetDtoList(@PathVariable Long userId){
      return lostPetService.getAllLostPetByUserId(userId);
    }

}
