package com.demo.healthcare.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Doctor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String specialization;

    @OneToMany(mappedBy = "doctor", cascade = {CascadeType.REMOVE, CascadeType.MERGE})
//    @OneToMany(mappedBy = "doctor", cascade = CascadeType.ALL)
    private List<Patient> patients;

    public Doctor() {
    }

    public Doctor(String name, String specialization) {
        this.name = name;
        this.specialization = specialization;
    }

    public Doctor(String name) {
        this(name , "");
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSpecialization() {
        return specialization;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }

    public List<Patient> getPatients() {
        return patients;
    }

    public void setPatients(List<Patient> patients) {
        this.patients = patients;
    }
}
