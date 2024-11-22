package com.quantumtech.mediapp_backend.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
//@Table(name = "patient_table")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Patient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idPatient;

    @Column(length = 70, nullable = false)
    private String firstName;
    
    @Column(length = 70, nullable = false)
    private String lastName;

    @Column(length = 8, nullable = false)
    private String dni;

    @Column(length = 150)
    private String address;

    @Column(length = 9, nullable = false)
    private String phone;

    @Column(length = 55, nullable = false)
    private String email;

}
