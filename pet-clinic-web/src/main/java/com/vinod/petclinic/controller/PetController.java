package com.vinod.petclinic.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class PetController {

    @RequestMapping({"/pets","/pres/index","/pets/index.html"})
    public  String list(){
        return "pet/index";
    }

}
