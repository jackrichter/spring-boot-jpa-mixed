package com.demo.healthcare.model;

import jakarta.persistence.*;

@Entity
public class MedicalRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String diagnosis;

    @OneToOne(mappedBy = "medicalRecord")   // Means, the Patient record owns the relationship. No FK in MedicalRecord table in db
    private Patient patient;

    public MedicalRecord() {
    }

    public MedicalRecord(String diagnosis) {
        this.diagnosis = diagnosis;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDiagnosis() {
        return diagnosis;
    }

    public void setDiagnosis(String diagnosis) {
        this.diagnosis = diagnosis;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    @Override
    public String toString() {
        return "MedicalRecord{" +
                "id=" + id +
                ", diagnosis='" + diagnosis + '\'' +
                ", patient=" + patient +
                '}';
    }
}
