package com.vinod.petclinic.service.map;

import com.vinod.petclinic.model.Owner;
import com.vinod.petclinic.service.CrudService;

public class OwnerServiceMap extends AbstractMapService<Owner,Long> implements CrudService<Owner,Long> {

    @Override
    public Owner save(Owner object) {
        return super.save(object.getId(),object);
    }
}
