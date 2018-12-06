package com.vinod.petclinic.service.map;

import com.vinod.petclinic.model.Vet;
import com.vinod.petclinic.service.VetService;

public class VetServiceMap extends AbstractMapService<Vet,Long> implements VetService {
    @Override
    public Vet save(Vet object) {
        return super.save(object.getId(),object);
    }
}
