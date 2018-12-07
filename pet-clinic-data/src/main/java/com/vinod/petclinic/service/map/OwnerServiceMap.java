package com.vinod.petclinic.service.map;

import com.vinod.petclinic.model.Owner;
import com.vinod.petclinic.service.OwnerService;
import org.springframework.stereotype.Service;

import java.util.Set;
@Service
public class OwnerServiceMap extends AbstractMapService<Owner,Long> implements OwnerService {

    @Override
    public Set<Owner> findAll() {
        return super.findAll();
    }

    @Override
    public Owner findById(Long aLong) {
        return super.findById(aLong);
    }

    @Override
    public void delete(Owner object) {
        super.delete(object);
    }

    @Override
    public void deleteById(Long aLong) {
        super.deleteById(aLong);
    }

    @Override
    public Owner save(Owner object) {
        return super.save(object);
    }

    @Override
    public Owner findByLastName(String lastName) {
        //return map.entrySet().forEach(entry -> entry.getValue().getLastName().equals(lastName));
        return null;
    }
}
