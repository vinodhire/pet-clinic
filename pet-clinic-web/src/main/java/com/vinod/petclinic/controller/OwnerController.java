package com.vinod.petclinic.controller;

import com.vinod.petclinic.model.Owner;
import com.vinod.petclinic.service.OwnerService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Set;

@Controller
@RequestMapping("/owners")
public class OwnerController {

    private OwnerService ownerService;

    public OwnerController(OwnerService ownerService) {
        this.ownerService = ownerService;
    }

    @RequestMapping({"","/","/index","/index.html"})
    public  String list(Model model){
        Set<Owner> owners = ownerService.findAll();
        model.addAttribute("owners",owners);
        System.out.println("Adding owners to model : "+owners);
        return "owner/index";
    }
}
