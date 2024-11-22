package com.quantumtech.mediapp_backend.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.quantumtech.mediapp_backend.model.Patient;
import com.quantumtech.mediapp_backend.repo.IGenericRepo;
import com.quantumtech.mediapp_backend.repo.IPatientRepo;
import com.quantumtech.mediapp_backend.service.IPatientService;

@Service
public class PatientServiceImpl extends CRUDImpl<Patient, Integer> implements IPatientService{

    @Autowired
    private IPatientRepo repo;

    @Override
    protected IGenericRepo<Patient, Integer> getRepo() {
        return repo;
    }

    @Override
    public Page<Patient> listPage(Pageable page) {
        return repo.findAll(page);
    }

}
