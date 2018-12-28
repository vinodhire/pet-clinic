package com.vinod.petclinic.controller;

import com.vinod.petclinic.model.Vet;
import com.vinod.petclinic.service.VetService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Set;

@RequestMapping("/vets")
@Controller
public class VetController  {

    private VetService vetService;

    public VetController(VetService vetService) {
        this.vetService = vetService;
    }

    @RequestMapping({"","/index","/index.html"})
    public  String list(Model model){
        Set<Vet> vets = vetService.findAll();
        model.addAttribute("vets",vets);
        System.out.println("Adding Vets to model : "+vets);
        return "vet/index";
    }
}
