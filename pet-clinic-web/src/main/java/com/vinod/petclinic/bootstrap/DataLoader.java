package com.vinod.petclinic.bootstrap;

import com.vinod.petclinic.model.Owner;
import com.vinod.petclinic.model.Pet;
import com.vinod.petclinic.model.PetType;
import com.vinod.petclinic.model.Vet;
import com.vinod.petclinic.service.OwnerService;
import com.vinod.petclinic.service.PetService;
import com.vinod.petclinic.service.VetService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class DataLoader implements CommandLineRunner {

    private OwnerService ownerService;
    private VetService vetService;
    private PetService petService;

    public DataLoader(OwnerService ownerService, VetService vetService, PetService petService) {
        this.ownerService = ownerService;
        this.vetService = vetService;
        this.petService = petService;
    }

    @Override
    public void run(String... args) throws Exception {

        // Adding some owners
        Owner owner1 = new Owner();
        owner1.setId(1L);
        owner1.setFirstName("Abhishek");
        owner1.setLastName("Bhardwaj");
        ownerService.save(owner1);

        Owner owner2 = new Owner();
        owner2.setId(1L);
        owner2.setFirstName("Kiran");
        owner2.setLastName("Patil");
        ownerService.save(owner2);

        // Adding some Vets
        Vet vet1 = new Vet();
        vet1.setId(1L);
        vet1.setFirstName("Jagan");
        vet1.setLastName("Pawar");
        vetService.save(vet1);

        Vet vet2 = new Vet();
        vet2.setId(1L);
        vet2.setFirstName("Shirish");
        vet2.setLastName("Pawar");
        vetService.save(vet1);

        // Adding some Pets
        Pet pet1= new Pet();
        pet1.setId(1L);
        pet1.setPetType(new PetType("Dog"));
        pet1.setOwner(owner1);
        pet1.setBirtDate(LocalDate.now());
        petService.save(pet1);

        Pet pet2= new Pet();
        pet1.setId(2L);
        pet1.setPetType(new PetType("Dog"));
        pet1.setOwner(owner2);
        pet1.setBirtDate(LocalDate.now());
        petService.save(pet2);

    }
}
