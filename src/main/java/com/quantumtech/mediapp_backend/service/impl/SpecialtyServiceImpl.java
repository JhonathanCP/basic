package com.quantumtech.mediapp_backend.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.quantumtech.mediapp_backend.model.Specialty;
import com.quantumtech.mediapp_backend.repo.IGenericRepo;
import com.quantumtech.mediapp_backend.repo.ISpecialtyRepo;
import com.quantumtech.mediapp_backend.service.ISpecialtyService;


@Service
public class SpecialtyServiceImpl extends CRUDImpl<Specialty, Integer> implements ISpecialtyService{

    @Autowired
    private ISpecialtyRepo repo;

    @Override
    protected IGenericRepo<Specialty, Integer> getRepo() {
        return repo;
    }

}
