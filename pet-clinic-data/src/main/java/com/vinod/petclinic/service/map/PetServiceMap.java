package com.vinod.petclinic.service.map;

import com.vinod.petclinic.model.Pet;
import com.vinod.petclinic.service.PerService;

public class PetServiceMap extends AbstractMapService<Pet,Long> implements PerService
{
    @Override
    public Pet save(Pet object) {
        return super.save(object.getId(),object);
    }
}
