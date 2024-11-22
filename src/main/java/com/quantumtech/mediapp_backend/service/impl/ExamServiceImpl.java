package com.quantumtech.mediapp_backend.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.quantumtech.mediapp_backend.model.Exam;
import com.quantumtech.mediapp_backend.repo.IGenericRepo;
import com.quantumtech.mediapp_backend.repo.IExamRepo;
import com.quantumtech.mediapp_backend.service.IExamService;


@Service
public class ExamServiceImpl extends CRUDImpl<Exam, Integer> implements IExamService{

    @Autowired
    private IExamRepo repo;

    @Override
    protected IGenericRepo<Exam, Integer> getRepo() {
        return repo;
    }

}
