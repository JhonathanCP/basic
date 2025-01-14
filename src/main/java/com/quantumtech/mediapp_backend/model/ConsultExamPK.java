package com.quantumtech.mediapp_backend.model;

import jakarta.persistence.Embeddable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

@Embeddable
@EqualsAndHashCode
public class ConsultExamPK implements Serializable{

    @EqualsAndHashCode.Include
    @ManyToOne
    @JoinColumn(name = "id_consult")
    private Consult consult;

    @EqualsAndHashCode.Include
    @ManyToOne
    @JoinColumn(name = "id_exam")
    private Exam exam;

}
