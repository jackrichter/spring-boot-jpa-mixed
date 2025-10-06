package com.demo.healthcare.model;

import jakarta.persistence.*;

@Entity
public class Patient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private int age;

    @OneToOne
    @JoinColumn(name = "medical_record")
    private MedicalRecord medicalRecord;    // Patient is Not the Owning side

    @ManyToOne(cascade = {CascadeType.PERSIST})
    @JoinColumn(name = "doctor_id")
    private Doctor doctor;     // Patient is the Owning side because the FK resides in the Patient table in db

    public Patient() {
    }

    public Patient(String name, int age) {
        this.name = name;
        this.age = age;
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

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public MedicalRecord getMedicalRecord() {
        return medicalRecord;
    }

    public void setMedicalRecord(MedicalRecord medicalRecord) {
        this.medicalRecord = medicalRecord;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }
}
