package com.quantumtech.mediapp_backend.controller;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.quantumtech.mediapp_backend.dto.ConsultExamDTO;
import com.quantumtech.mediapp_backend.model.ConsultExam;
import com.quantumtech.mediapp_backend.service.IConsultExamService;

@RestController
@RequestMapping("/consultexams")
public class ConsultExamController {

    @Autowired
    private ModelMapper mapper;

    @Autowired
    private IConsultExamService service;

    @GetMapping("/{idConsult}")
    public ResponseEntity<List<ConsultExamDTO>> getConsultsById(@PathVariable Integer idConsult) {
        
        List<ConsultExam> lst = service.getExamsByConsultId(idConsult);
        List<ConsultExamDTO> lstDTO = mapper.map(lst, new TypeToken<List<ConsultExamDTO>>() {}.getType());
        return new ResponseEntity<>(lstDTO, HttpStatus.OK);
    }

}
