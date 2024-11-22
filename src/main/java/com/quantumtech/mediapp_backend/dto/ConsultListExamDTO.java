package com.quantumtech.mediapp_backend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.validation.constraints.*;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ConsultListExamDTO {

    //@JsonProperty(value = "consulta_property")
    @NotNull
    private ConsultDTO consult;

    @NotNull
    private List<ExamDTO> lstExam;
    
}