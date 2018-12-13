package com.vinod.petclinic.service;

import com.vinod.petclinic.model.Speciality;
import com.vinod.petclinic.repository.SpecialityRepository;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
@Profile("SpringDataJPA")
public class SpecialityServiceImpl implements SpecialityService {

    private SpecialityRepository specialityRepository;

    public SpecialityServiceImpl(SpecialityRepository specialityRepository) {
        this.specialityRepository = specialityRepository;
    }

    @Override
    public Set<Speciality> findAll() {
        Set<Speciality> specialities = new HashSet<>();
        this.specialityRepository.findAll().forEach(specialities::add);
        return specialities;
    }

    @Override
    public Speciality findById(Long id) {
        return this.specialityRepository.findById(id).orElse(null);
    }

    @Override
    public Speciality save(Speciality speciality) {
        return this.specialityRepository.save(speciality);
    }

    @Override
    public void deleteById(Long id) {
        this.specialityRepository.deleteById(id);
    }

    @Override
    public void delete(Speciality speciality) {
        this.specialityRepository.delete(speciality);
    }
}
