package com.quantumtech.mediapp_backend.service;

import java.util.List;

import org.springframework.data.repository.query.Param;

import com.quantumtech.mediapp_backend.model.ConsultExam;

public interface IConsultExamService {

    List<ConsultExam> getExamsByConsultId(@Param("idConsult") Integer idConsult);

}
