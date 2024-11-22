package com.quantumtech.mediapp_backend.service;

import java.time.LocalDateTime;
import java.util.List;

import com.quantumtech.mediapp_backend.dto.ConsultProcDTO;
import com.quantumtech.mediapp_backend.model.Consult;
import com.quantumtech.mediapp_backend.model.Exam;

public interface IConsultService extends ICRUD<Consult, Integer>{

    Consult saveTransactional(Consult consult, List<Exam> exams);

    List<Consult> search(String dni, String fullname);

    List<Consult> searchByDates(LocalDateTime date1, LocalDateTime date2);

    List<ConsultProcDTO> callProcedureOrFunction();

    byte[] generateReport() throws Exception;
    
}