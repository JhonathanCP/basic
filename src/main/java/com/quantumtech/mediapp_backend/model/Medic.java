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
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Medic {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idMedic;

    @Column(length = 70, nullable = false)
    private String firstName;
    
    @Column(length = 70, nullable = false)
    private String lastName;

    @Column(length = 12, nullable = false, unique = true)
    private String cmp;

    private String photoUrl;

}
