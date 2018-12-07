package com.vinod.petclinic.service.map;

import com.vinod.petclinic.model.Pet;
import com.vinod.petclinic.service.PetService;

import java.util.Set;

public class PetServiceMap extends AbstractMapService<Pet,Long> implements PetService
{

    @Override
    public Set<Pet> findAll() {
        return super.findAll();
    }

    @Override
    public Pet findById(Long aLong) {
        return super.findById(aLong);
    }

    @Override
    public Pet save(Long aLong, Pet object) {
        return super.save(aLong, object);
    }

    @Override
    public void delete(Pet object) {
        super.delete(object);
    }

    @Override
    public void deleteById(Long aLong) {
        super.deleteById(aLong);
    }

    @Override
    public Pet save(Pet object) {
        return super.save(object.getId(),object);
    }
}
