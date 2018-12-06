package com.vinod.petclinic.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/vets")
public class VetController  {

    @RequestMapping({"","/","/index","/index.html"})
    public  String list(){
        return "vet/index";
    }
}
