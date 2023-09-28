package com.devmountain.whereMyPet.repositories;

import com.devmountain.whereMyPet.entities.FoundPet;
import com.devmountain.whereMyPet.entities.LostPet;
import com.devmountain.whereMyPet.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FoundPetRepository extends JpaRepository<FoundPet, Long> {
    List<FoundPet> findAllByUserEquals(User user);

    List<FoundPet> findAll();
}




