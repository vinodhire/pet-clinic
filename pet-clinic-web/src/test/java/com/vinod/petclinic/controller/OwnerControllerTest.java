package com.vinod.petclinic.controller;

import com.vinod.petclinic.model.Owner;
import com.vinod.petclinic.service.OwnerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.HashSet;
import java.util.Set;

import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.mockito.Mockito.verifyZeroInteractions;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
class OwnerControllerTest {

    @Mock
    OwnerService ownerService;

    @InjectMocks
    OwnerController ownerController;

    Set<Owner> owners;

    MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        // Building some dummy data for mocks
        owners = new HashSet<>();
        owners.add(Owner.builder().id(1L).firstName("Vinod").lastName("Hire").build());
        owners.add(Owner.builder().id(2L).firstName("Viraj").lastName("Hire").build());
        owners.add(Owner.builder().id(3L).firstName("Rajvi").lastName("Hire").build());
        owners.add(Owner.builder().id(4L).firstName("Rajshree").lastName("Hire").build());

        // Building MockMVC for controller
        mockMvc = MockMvcBuilders.standaloneSetup(ownerController).build();
    }

    @Test
    void listOwners() {

        // When - Then
        when(ownerService.findAll()).thenReturn(owners);

        try {
            mockMvc.perform(MockMvcRequestBuilders
                    .get("/owners"))
                    .andExpect(status().isOk())
                    .andExpect(view().name("owner/index"))
                    .andExpect(model().attribute("owners",hasSize(4)));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    void listOwnersByIndex() {

        // When - Then
        when(ownerService.findAll()).thenReturn(owners);

        try {
            mockMvc.perform(MockMvcRequestBuilders
                    .get("/owners/index"))
                    .andExpect(status().isOk())
                    .andExpect(view().name("owner/index"))
                    .andExpect(model().attribute("owners",hasSize(4)));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    void findOwners() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.get("/owners/find"))
                .andExpect(status().isOk())
                .andExpect(view().name("notimplemented"));

        verifyZeroInteractions(ownerService);
    }
}