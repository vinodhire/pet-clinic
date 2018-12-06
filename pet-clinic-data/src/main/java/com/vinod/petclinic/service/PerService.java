package com.vinod.petclinic.service;

import com.vinod.petclinic.model.Pet;

import java.util.Set;

public interface PerService {

    Pet findById(Long id);

    Pet save(Pet pet);

    Set<Pet> findAll();

}
