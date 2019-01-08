package com.vinod.petclinic.controller;

import com.vinod.petclinic.model.Owner;
import com.vinod.petclinic.service.OwnerService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.Map;
import java.util.Set;

@Controller
@RequestMapping("/owners")
public class OwnerController {

    public static final String OWNER_INDEX = "owner/index";
    public static final String OWNER_FIND_OWNERS = "owner/findOwners";
    public static final String OWNERS = "owners";
    public static final String OWNER = "owner";
    public static final String OWNER_OWNERS_LIST = "owner/ownersList";
    public static final String OWNER_CREATE_OR_UPDATE_OWNER_FORM = "owner/createOrUpdateOwnerForm";
    public static final String REJECTION_MESSAGE = "Please provide valid details";
    public static final String OWNER_OWNER_DETAILS = "/owner/ownerDetails";
    private OwnerService ownerService;

    public OwnerController(OwnerService ownerService) {
        this.ownerService = ownerService;
    }

    @InitBinder
    public void setAllowedFields(WebDataBinder dataBinder){
        dataBinder.setDisallowedFields("id");
    }

    @RequestMapping({"","/","/index","/index.html"})
    public  String listOwners(Model model){
        Set<Owner> owners = ownerService.findAll();
        model.addAttribute(OWNERS,owners);
        System.out.println("Adding owners to model : "+owners);
        return OWNER_INDEX;
    }



    @GetMapping("/find")
    public String initFindForm(Map<String,Object> model){
        model.put(OWNER,new Owner());
        return OWNER_FIND_OWNERS;
    }

    @PostMapping("/find")
    public String processFindForm(Owner owner, BindingResult bindingResult,Map<String,Object> model){

        // Check if last name provided
        if (owner.getLastName() == null){
            owner.setLastName("");
        }

        // Find owner(S) by provided lastname
        Set<Owner> ownersFound = ownerService.findAllByLastNameLike("%"+owner.getLastName()+"%");

        // Add Owner(s) to model
        if (ownersFound.isEmpty()){
            bindingResult.rejectValue("lastName","notFound","Not Found");
            return OWNER_FIND_OWNERS;
        }else if (ownersFound.size()==1){
            return "redirect:/owners/"+ownersFound.iterator().next().getId();
        }else {
            model.put("selections",ownersFound);
            return OWNER_OWNERS_LIST;
        }
    }

    @GetMapping("/new")
    public String newOwnerForm(Map<String,Object> model){
        model.put(OWNER,Owner.builder().build());
        return OWNER_CREATE_OR_UPDATE_OWNER_FORM;
    }

    @PostMapping(value = {"/","/new","/save"})
    public String processCreationForm(@Valid Owner owner, BindingResult bindingResult, Map<String,Object> model){
        // check if owner is null
        /*if(owner ==null){
            bindingResult.rejectValue(OWNERS, REJECTION_MESSAGE);
        }*/
        if (bindingResult.hasErrors()){
            return OWNER_CREATE_OR_UPDATE_OWNER_FORM;
        }
        Owner savedOwner = ownerService.save(owner);
        return "redirect:/owners/"+savedOwner.getId();
    }

    @GetMapping("/{id}/edit")
    public String updateOwnerForm(@PathVariable("id") Long ownerId,Model model){
        Owner ownerFound = ownerService.findById(ownerId);
        model.addAttribute(OWNER,ownerFound);
        return OWNER_CREATE_OR_UPDATE_OWNER_FORM;
    }

    @PostMapping(value = {"/{id}/edit"})
    public String processUpdationForm(@Valid Owner owner, BindingResult bindingResult,@PathVariable("id") Long ownerId){
        // check if owner is null
        /*if(owner ==null){
            bindingResult.rejectValue(OWNERS, REJECTION_MESSAGE);
        }*/
        if (bindingResult.hasErrors()){
            return OWNER_CREATE_OR_UPDATE_OWNER_FORM;
        }
        owner.setId(ownerId);
        Owner savedOwner = ownerService.save(owner);
        return "redirect:/owners/"+savedOwner.getId();
    }


    @GetMapping("/{id}")
    public ModelAndView showOwner(@PathVariable Long id){
        ModelAndView modelAndView = new ModelAndView(OWNER_OWNER_DETAILS);
        Owner owner = ownerService.findById(id);
        modelAndView.addObject(owner);
        return modelAndView;
    }
}
