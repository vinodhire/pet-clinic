package com.vinod.petclinic.bootstrap;

import com.vinod.petclinic.model.Owner;
import com.vinod.petclinic.model.Pet;
import com.vinod.petclinic.model.PetType;
import com.vinod.petclinic.model.Vet;
import com.vinod.petclinic.service.OwnerService;
import com.vinod.petclinic.service.PetService;
import com.vinod.petclinic.service.PetTypeService;
import com.vinod.petclinic.service.VetService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class DataLoader implements CommandLineRunner {

    private OwnerService ownerService;
    private VetService vetService;
    private PetService petService;
    private PetTypeService petTypeService;

    public DataLoader(OwnerService ownerService, VetService vetService, PetService petService, PetTypeService petTypeService) {
        this.ownerService = ownerService;
        this.vetService = vetService;
        this.petService = petService;
        this.petTypeService = petTypeService;
    }

    @Override
    public void run(String... args) throws Exception {

        PetType dog = new PetType();
        dog.setName("Dog");
        PetType savedDog = petTypeService.save(dog);

        PetType cat = new PetType();
        cat.setName("Dog");
        PetType savedCat = petTypeService.save(cat);

        // Adding some owners
        Owner abhishek = new Owner();
        //owner1.setId(1L);
        abhishek.setFirstName("Abhishek");
        abhishek.setLastName("Bhardwaj");
        abhishek.setAddress("Mukai Chauk");
        abhishek.setCity("Pune");
        abhishek.setTelephone("9123456789");
        ownerService.save(abhishek);

        Owner kiran = new Owner();
        //owner2.setId(2L);
        kiran.setFirstName("Kiran");
        kiran.setLastName("Patil");
        abhishek.setAddress("KJ Road");
        abhishek.setCity("Chalishgaon");
        abhishek.setTelephone("916667778");
        ownerService.save(kiran);

        // Adding some Vets
        Vet jagan = new Vet();
        jagan.setId(1L);
        jagan.setFirstName("Jagan");
        jagan.setLastName("Pawar");
        vetService.save(jagan);

        Vet shirish = new Vet();
        shirish.setId(2L);
        shirish.setFirstName("Shirish");
        shirish.setLastName("Pawar");
        vetService.save(shirish);

        // Adding some Pets
        Pet abhisDog= new Pet();
        abhisDog.setId(2L);
        abhisDog.setName("Tommy");
        abhisDog.setPetType(savedDog);
        abhisDog.setOwner(abhishek);
        abhisDog.setBirtDate(LocalDate.now());
        petService.save(abhisDog);

        // Updating Owner
        abhishek.getPets().add(abhisDog);
        ownerService.save(abhishek);

        Pet kiransCat= new Pet();
        kiransCat.setId(2L);
        kiransCat.setName("Kitty");
        kiransCat.setPetType(savedCat);
        kiransCat.setOwner(kiran);
        kiransCat.setBirtDate(LocalDate.now());
        petService.save(kiransCat);

        // Updating Owner
        kiran.getPets().add(kiransCat);
        ownerService.save(kiran);

    }
}
