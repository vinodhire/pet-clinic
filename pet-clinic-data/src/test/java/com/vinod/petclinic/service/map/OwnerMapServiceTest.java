package com.vinod.petclinic.service.map;

import com.vinod.petclinic.model.Owner;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class OwnerMapServiceTest {

    private  OwnerMapService ownerMapService;
    private Long ownerId = 1L;
    private String lastName = "Hire";

    @BeforeEach
    void setUp() {
        ownerMapService = new OwnerMapService(new PetTypeMapService(),new PetMapService());
        ownerMapService.save(Owner.builder().id(ownerId).lastName(lastName).build());
    }

    @Test
    void findAll() {
        Set<Owner> owners =  ownerMapService.findAll();
        assertEquals(1L,owners.size());
    }

    @Test
    void findById() {
        Owner owner = ownerMapService.findById(ownerId);
        assertEquals(ownerId,owner.getId());
    }

    @Test
    void delete() {
        ownerMapService.delete(ownerMapService.findById(ownerId));
        assertEquals(0,ownerMapService.findAll().size());
    }

    @Test
    void deleteById() {
        ownerMapService.deleteById(ownerId);
        assertEquals(0,ownerMapService.findAll().size());
    }

    @Test
    void saveExistingId() {
        Long id = 2L;
        Owner owner = Owner.builder().id(id).build();
        Owner savedOwner =  ownerMapService.save(owner);
        assertEquals(id,savedOwner.getId());
    }

    @Test
    void saveNoId() {
        Owner owner = Owner.builder().firstName("Owner1").build();
        Owner savedOwner =  ownerMapService.save(owner);
        assertNotNull(savedOwner);
        assertNotNull(savedOwner.getId());
    }

    @Test
    void findByLastName() {
        Owner owner =  ownerMapService.findByLastName(lastName);
        assertNotNull(owner);
        assertEquals(ownerId,owner.getId());
        assertEquals(lastName,owner.getLastName());
    }
}