package com.devmountain.whereMyPet.services;

import com.devmountain.whereMyPet.dtos.FoundPetDto;
import com.devmountain.whereMyPet.entities.FoundPet;
import com.devmountain.whereMyPet.entities.User;
import com.devmountain.whereMyPet.repositories.FoundPetRepository;
import com.devmountain.whereMyPet.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class FoundPetServiceImpl implements FoundPetService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private FoundPetRepository foundPetRepository;

    //create a method to add a found pet
    @Override
    @Transactional
    public void addFoundPet(FoundPetDto foundPetDto, Long userId){
        Optional<User> userOptional = userRepository.findById((userId));
        FoundPet foundPet = new FoundPet(foundPetDto);
        userOptional.ifPresent(foundPet::setUser);
        foundPetRepository.saveAndFlush(foundPet);
    }

    //create a method to delete a found pet
    @Override
    @Transactional
    public void deleteFoundPetById(Long foundPetId){
        Optional<FoundPet> foundPetOptional = foundPetRepository.findById(foundPetId);
        foundPetOptional.ifPresent(foundPet -> foundPetRepository.delete(foundPet));
    }

    //create a method to update a found pet
    @Override
    @Transactional
    public void updateFoundPetById(FoundPetDto foundPetDto, Long foundPetId) {
      //  Optional<FoundPet> foundPetOptional = foundPetRepository.findById(foundPetDto.getId());
        Optional<FoundPet> foundPetOptional = foundPetRepository.findById(foundPetId);
        foundPetOptional.ifPresent(foundPet -> {
            foundPet.setPetType(foundPetDto.getPetType());
            foundPet.setFound(foundPetDto.getFound());
            foundPet.setGender(foundPetDto.getGender());
            foundPet.setContactEmail(foundPetDto.getContactEmail());
            foundPet.setDate(foundPetDto.getDate());
            foundPetRepository.saveAndFlush(foundPet);
        });
    }
        //create a method that find all found pet by a user
        @Override
        public List<FoundPetDto> getAllFoundPetByUserId(Long userId){
            Optional<User> userOptional = userRepository.findById(userId);
            if(userOptional.isPresent()){
                List<FoundPet> foundPetList = foundPetRepository.findAllByUserEquals(userOptional.get());
                return foundPetList.stream().map(foundPet -> new FoundPetDto(foundPet)).collect(Collectors.toList());

            }
            return Collections.emptyList();
        }

        //create a method that find a found pet by found pet's id.
        @Override
        public Optional<FoundPetDto> getFoundPetById(Long foundPetId){
        Optional<FoundPet> foundPetOptional = foundPetRepository.findById((foundPetId));
        if(foundPetOptional.isPresent()){
            return Optional.of(new FoundPetDto(foundPetOptional.get()));
        }
        return Optional.empty();
        }

        //create a method that retrieve all found pets
        @Override
        public List<FoundPetDto> getAllFoundPets(){
        List<FoundPet> foundPetList = foundPetRepository.findAll();
        return foundPetList.stream().map(foundPet -> new FoundPetDto(foundPet)).collect(Collectors.toList());
        }
}


