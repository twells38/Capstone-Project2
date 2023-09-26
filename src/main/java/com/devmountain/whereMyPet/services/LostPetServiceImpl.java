package com.devmountain.whereMyPet.services;

import com.devmountain.whereMyPet.dtos.LostPetDto;
import com.devmountain.whereMyPet.entities.LostPet;
import com.devmountain.whereMyPet.entities.User;
import com.devmountain.whereMyPet.repositories.LostPetRepository;
import com.devmountain.whereMyPet.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class LostPetServiceImpl implements LostPetService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private LostPetRepository lostPetRepository;

    //create method addLostPet
    @Override
    @Transactional
    public void addLostPet(LostPetDto lostPetDto, Long userId){
        Optional<User> userOptional = userRepository.findById(userId);
        LostPet lostPet = new LostPet(lostPetDto);
        userOptional.ifPresent(lostPet::setUser);
        lostPetRepository.saveAndFlush(lostPet);
    }

    //create method to delete lostPet
    @Override
    @Transactional
    public void deleteLostPetById(Long lostPetId){
        Optional<LostPet> lostPetOptional = lostPetRepository.findById(lostPetId);
        lostPetOptional.ifPresent(lostPet -> lostPetRepository.delete(lostPet));
    }

    //create method to update lostPet
    @Override
    @Transactional
    public void updateLostPetById(LostPetDto lostPetDto, Long lostPetId){
      //  Optional<LostPet> lostPetOptional = lostPetRepository.findById(lostPetDto.getId());
        Optional<LostPet> lostPetOptional = lostPetRepository.findById(lostPetId);

        lostPetOptional.ifPresent(lostPet ->{
           lostPet.setPetName(lostPetDto.getPetName());
           lostPet.setPetType(lostPetDto.getPetType());
           lostPet.setGender(lostPetDto.getGender());
           lostPet.setContactEmail(lostPetDto.getContactEmail());
           lostPet.setLastSeen(lostPetDto.getLastSeen());
           lostPet.setDate(lostPetDto.getDate());
           lostPetRepository.saveAndFlush(lostPet);
        });
    }

    //create a method to find all lost pet by user
    @Override
    public List<LostPetDto> getAllLostPetByUserId(Long userId){
        Optional<User> userOptional = userRepository.findById(userId);
        if(userOptional.isPresent()){
            List<LostPet> lostPetList = lostPetRepository.findAllByUserEquals(userOptional.get());
            return lostPetList.stream().map(lostPet -> new LostPetDto(lostPet)).collect(Collectors.toList());

        }
        return Collections.emptyList();
    }

    //create a method to find a lost pet by lost pet's id
    @Override
    public Optional<LostPetDto> getLostPetById(Long lostPetId){
        Optional<LostPet> lostPetOptional = lostPetRepository.findById(lostPetId);
        if(lostPetOptional.isPresent()){
            return Optional.of(new LostPetDto(lostPetOptional.get()));
        }
      return Optional.empty();
    }
}
