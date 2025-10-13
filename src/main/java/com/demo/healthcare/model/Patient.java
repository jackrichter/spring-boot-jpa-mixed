package com.demo.healthcare.model;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "new_patients")
public class Patient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private int age;

    @Embedded
    private Address address;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    @Transient
    private String ageGroup;
    @Lob
    private byte[] profilePicture;
            // CLOB -> Character Large Object
            // BLOB -> Binary Large Object


    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)        // The default Fetch Type is Eager for OneToOne
    @JoinColumn(name = "medical_record")
    private MedicalRecord medicalRecord;    // Patient is Not the Owning side

    @ManyToOne(cascade = {CascadeType.PERSIST},  fetch = FetchType.LAZY)    // The default is Eager for ManyToOne
    @JoinColumn(name = "doctor_id")
    private Doctor doctor;     // Patient is the Owning side because the FK resides in the Patient table in db

    @OneToMany(mappedBy = "patientId", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Prescription> prescriptions;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;

    public Patient() {
    }

    public Patient(String name, int age) {
        this.name = name;
        this.age = age;
        this.ageGroup = calculateAgeGroup(age);
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

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public void setAge(int age) {
        this.age = age;
        this.ageGroup = calculateAgeGroup(age);
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public byte[] getProfilePicture() {
        return profilePicture;
    }

    public void setProfilePicture(byte[] profilePicture) {
        this.profilePicture = profilePicture;
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

    public List<Prescription> getPrescriptions() {
        return prescriptions;
    }

    public void setPrescriptions(List<Prescription> prescriptions) {
        this.prescriptions = prescriptions;
    }

    private String calculateAgeGroup(int age) {
        if (age <= 12) return "Child";
        else if (age <= 19) return "Teen";
        else if (age <= 59) return "Adult";
        else return "Senior";
    }
}
