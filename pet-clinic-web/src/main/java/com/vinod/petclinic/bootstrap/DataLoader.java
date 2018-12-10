package com.vinod.petclinic.bootstrap;

import com.vinod.petclinic.model.*;
import com.vinod.petclinic.service.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class DataLoader implements CommandLineRunner {

    private OwnerService ownerService;
    private VetService vetService;
    private PetService petService;
    private PetTypeService petTypeService;
    private SpecialityService specialityService;

    public DataLoader(OwnerService ownerService, VetService vetService, PetService petService, PetTypeService petTypeService, SpecialityService specialityService) {
        this.ownerService = ownerService;
        this.vetService = vetService;
        this.petService = petService;
        this.petTypeService = petTypeService;
        this.specialityService = specialityService;
    }

    @Override
    public void run(String... args) throws Exception {
        loadData();
    }

    private void loadData() {
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

        Speciality radiology = new Speciality();
        radiology.setDescription("Radiology");
        Speciality savedRadiology = specialityService.save(radiology);

        Speciality surgery = new Speciality();
        surgery.setDescription("Surgery");
        Speciality savedSurgery = specialityService.save(surgery);

        Speciality dentistry = new Speciality();
        dentistry.setDescription("Dentistry");
        Speciality savedDentistry = specialityService.save(dentistry);

        // Adding some Vets
        Vet jagan = new Vet();
        jagan.setId(1L);
        jagan.setFirstName("Jagan");
        jagan.setLastName("Pawar");
        jagan.getSpecialities().add(savedRadiology);
        vetService.save(jagan);

        Vet shirish = new Vet();
        shirish.setId(2L);
        shirish.setFirstName("Shirish");
        shirish.setLastName("Pawar");
        shirish.getSpecialities().add(savedSurgery);
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
