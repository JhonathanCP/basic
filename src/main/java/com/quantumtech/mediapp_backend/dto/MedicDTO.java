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
public class MedicDTO {

    @EqualsAndHashCode.Include
    private Integer idMedic;

    @NotNull(message = "{firstname.null}")
    @Size(min = 3, message = "{firstname.size}")
    private String firstName;

    @NotNull
    @Size(min = 3, message = "{lastname.size}")
    private String lastName;

    @Size(max = 12)
    private String cmp;

    @Size(min = 3, max = 200)
    private String photoUrl;

    /*@Max(1)
    @Min(34)
    private Integer edad;

    @Pattern(regexp = "")
    private String test;*/
}