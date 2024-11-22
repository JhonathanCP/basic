package com.quantumtech.mediapp_backend.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.quantumtech.mediapp_backend.model.ConsultExam;
import com.quantumtech.mediapp_backend.repo.IConsultExamRepo;
import com.quantumtech.mediapp_backend.service.IConsultExamService;

@Service
public class ConsultExamImpl implements IConsultExamService{

    @Autowired
    private IConsultExamRepo repo;

    @Override
    public List<ConsultExam> getExamsByConsultId(Integer idConsult) {
        return repo.getExamsByConsultId(idConsult);
    }

}
