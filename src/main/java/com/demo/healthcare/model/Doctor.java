package com.demo.healthcare.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Doctor extends Person {

    private String specialization;

    @OneToMany(mappedBy = "doctor", cascade = {CascadeType.REMOVE, CascadeType.MERGE})
//    @OneToMany(mappedBy = "doctor", cascade = CascadeType.ALL)
    private List<Patient> patients;

    @OneToMany(mappedBy = "doctorId", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Prescription> prescriptions;

    public Doctor() {
    }

    public Doctor(String name, int age, String email, String specialization) {
        super(name, age, email);
        this.specialization = specialization;
    }

    public Doctor(String name, String specialization) {
        this(name, 0, null, specialization);
        this.specialization = specialization;
    }

    public Doctor(String name) {
        this(name , "");
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

    @Override
    public String toString() {
        return "Doctor" + super.toString();
    }
}
