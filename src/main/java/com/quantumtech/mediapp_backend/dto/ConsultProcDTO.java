package com.quantumtech.mediapp_backend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ConsultProcDTO {

    private Integer quantity;

    private String consultdate;

}