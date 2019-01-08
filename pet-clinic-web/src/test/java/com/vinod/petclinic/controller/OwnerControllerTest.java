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
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
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
                    .andExpect(model().attribute("owners", hasSize(4)));
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
                    .andExpect(model().attribute("owners", hasSize(4)));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    void initFindForm() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.get("/owners/find"))
                .andExpect(status().isOk())
                .andExpect(view().name("owner/findOwners"))
                .andExpect(model().attributeExists("owner"));

        verifyZeroInteractions(ownerService);
    }

    @Test
    void processFindFormAndReturnMany() throws Exception {

        // Given - When - Then
        when(ownerService.findAllByLastNameLike(anyString())).thenReturn(owners);

        mockMvc.perform(post("/owners/find"))
                .andExpect(status().isOk())
                .andExpect(view().name("owner/ownersList"))
                .andExpect(model().attributeExists("selections"))
                .andExpect(model().attribute("selections", hasSize(4)));
    }

    @Test
    void processFindFormAndReturnOne() throws Exception {

        Set<Owner> owners = new HashSet<>();
        owners.add(Owner.builder().id(1L).firstName("Vinod").lastName("Hire").build());

        // Given - When - Then
        when(ownerService.findAllByLastNameLike(anyString())).thenReturn(owners);

        mockMvc.perform(post("/owners/find"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/owners/1"));
                //.andExpect(model().attributeExists("owners"))
                //.andExpect(model().attribute("owner", hasSize(1)));
    }

    @Test
    void newOwnerForm() throws Exception {

        mockMvc.perform(get("/owners/new"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("owner"))
                .andExpect(view().name("owner/createOrUpdateOwnerForm"));

        verifyZeroInteractions(ownerService);
    }


    @Test
    void processCreationForm() throws Exception {

        when(ownerService.save(any())).thenReturn(Owner.builder().id(1L).build());

        mockMvc.perform(post("/owners/new"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/owners/1"));

        verify(ownerService,times(1)).save(any());
        //verify()
    }

    @Test
    void updateOwnerForm() throws Exception {

        when(ownerService.findById(anyLong())).thenReturn(Owner.builder().id(1L).build());

        mockMvc.perform(get("/owners/1/edit"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("owner"))
                .andExpect(view().name("owner/createOrUpdateOwnerForm"));
    }

    @Test
    void processUpdationForm() throws Exception {

        when(ownerService.save(any())).thenReturn(Owner.builder().id(1L).build());

        mockMvc.perform(post("/owners/save"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/owners/1"));
    }
}