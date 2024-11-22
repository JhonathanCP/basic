package com.quantumtech.mediapp_backend.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@IdClass(ConsultExamPK.class)
public class ConsultExam {


    @Id
    private Consult consult;

    @Id
    private Exam exam;

}
