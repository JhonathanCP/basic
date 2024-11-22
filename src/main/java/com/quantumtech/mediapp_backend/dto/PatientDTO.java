package com.quantumtech.mediapp_backend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import jakarta.validation.constraints.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true )
public class PatientDTO {

    @EqualsAndHashCode.Include
    private Integer idPatient;

    @NotNull(message = "{firstname.null}")
    @Size(min = 3, message = "{firstname.size}")
    private String firstName;

    @NotNull
    @Size(min = 3, message = "{lastname.size}")
    private String lastName;

    @Size(min = 8)
    private String dni;

    @Size(min = 3, max = 150)
    private String address;

    @Size(min = 9, max = 9)
    private String phone;

    @Email
    private String email;

    /*@Max(1)
    @Min(34)
    private Integer edad;

    @Pattern(regexp = "")
    private String test;*/
}