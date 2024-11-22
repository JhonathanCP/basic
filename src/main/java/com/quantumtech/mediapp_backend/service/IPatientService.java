package com.quantumtech.mediapp_backend.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.quantumtech.mediapp_backend.model.Patient;

public interface IPatientService extends ICRUD<Patient, Integer>{

    Page<Patient> listPage(Pageable page);

}