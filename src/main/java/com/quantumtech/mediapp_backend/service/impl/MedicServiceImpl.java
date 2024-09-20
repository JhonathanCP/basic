package com.quantumtech.mediapp_backend.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.quantumtech.mediapp_backend.model.Medic;
import com.quantumtech.mediapp_backend.repo.IGenericRepo;
import com.quantumtech.mediapp_backend.repo.IMedicRepo;
import com.quantumtech.mediapp_backend.service.IMedicService;


@Service
public class MedicServiceImpl extends CRUDImpl<Medic, Integer> implements IMedicService{

    @Autowired
    private IMedicRepo repo;

    @Override
    protected IGenericRepo<Medic, Integer> getRepo() {
        return repo;
    }

}
