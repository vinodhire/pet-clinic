package com.vinod.petclinic.service;

import com.vinod.petclinic.model.Owner;

public interface OwnerService extends CrudService<Owner,Long> {

    Owner findByLastName(String lastName);
}
