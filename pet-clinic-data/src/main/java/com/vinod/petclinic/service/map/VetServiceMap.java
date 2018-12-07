package com.vinod.petclinic.service.map;

import com.vinod.petclinic.model.Vet;
import com.vinod.petclinic.service.VetService;

import java.util.Set;

public class VetServiceMap extends AbstractMapService<Vet,Long> implements VetService {

    @Override
    public Set<Vet> findAll() {
        return super.findAll();
    }

    @Override
    public Vet findById(Long aLong) {
        return super.findById(aLong);
    }

    @Override
    public Vet save(Long aLong, Vet object) {
        return super.save(aLong, object);
    }

    @Override
    public void delete(Vet object) {
        super.delete(object);
    }

    @Override
    public void deleteById(Long aLong) {
        super.deleteById(aLong);
    }

    @Override
    public Vet save(Vet object) {
        return super.save(object.getId(),object);
    }
}
