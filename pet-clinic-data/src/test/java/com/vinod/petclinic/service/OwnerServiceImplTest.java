package com.vinod.petclinic.service;

import com.vinod.petclinic.model.Owner;
import com.vinod.petclinic.repository.OwnerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class OwnerServiceImplTest {

    private String lastName = "Smith";
    private Long ownerId = 1L;
    private Owner returnOwner;

    @Mock
    private OwnerRepository ownerRepository;

    @InjectMocks
    private OwnerServiceImpl ownerService;


    @BeforeEach
    void setUp() {
        //MockitoAnnotations.initMocks(this.getClass());
        returnOwner = Owner.builder().id(1L).lastName("Smith").build();
    }

    @Test
    void findByLastName() {
        // Given
        // When - Any - Then
        when(ownerRepository.findByLastName(anyString())).thenReturn(returnOwner);

        Owner owner = ownerService.findByLastName(lastName);
        assertNotNull(owner);
        assertEquals(lastName,owner.getLastName());
        verify(ownerRepository).findByLastName(anyString());
        verify(ownerRepository,times(1)).findByLastName(anyString());
    }

    @Test
    void findAll() {
        // Given
        Set<Owner> ownerSet =  new HashSet<>();
        ownerSet.add(Owner.builder().id(1L).build());
        ownerSet.add(Owner.builder().id(2L).build());

        // When - Then
        when(ownerRepository.findAll()).thenReturn(ownerSet);

        Set<Owner> owners =  ownerService.findAll();
        assertNotNull(owners);
        assertEquals(2,owners.size());
    }

    @Test
    void findById() {
        when(ownerRepository.findById(any())).thenReturn(Optional.of(returnOwner));
        Owner owner = ownerService.findById(ownerId);
        assertNotNull(owner);
    }

    @Test
    void findByIdNotFound() {
        when(ownerRepository.findById(any())).thenReturn(Optional.empty());
        Owner owner = ownerService.findById(ownerId);
        assertNull(owner);
    }


    @Test
    void save() {
        Owner detachedOwner = Owner.builder().id(1L).build();
        when(ownerRepository.save(any())).thenReturn(returnOwner);
        Owner savedOwner = ownerService.save(detachedOwner);
        assertNotNull(savedOwner);
    }

    @Test
    void deleteById() {
        ownerService.deleteById(ownerId);
        verify(ownerRepository,times(1)).deleteById(any());
    }

    @Test
    void delete() {
        ownerService.delete(returnOwner);
        verify(ownerRepository,times(1)).delete(any());
    }
}