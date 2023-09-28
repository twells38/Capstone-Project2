package com.devmountain.whereMyPet.repositories;

import com.devmountain.whereMyPet.entities.LostPet;
import com.devmountain.whereMyPet.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.List;

@Repository
public interface LostPetRepository extends JpaRepository<LostPet,Long> {
    List<LostPet> findAllByUserEquals(User user);
    List<LostPet> findAll();
}
