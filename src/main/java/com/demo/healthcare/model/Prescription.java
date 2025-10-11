package com.demo.healthcare.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
//@IdClass(PrescriptionId.class)
public class Prescription {

//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;

    @EmbeddedId
    private PrescriptionId id;

    private String notes;

//    @Id
    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @MapsId("doctorId")
    @JoinColumn(name = "doctor_id", nullable = false)
    private Doctor doctorId;

//    @Id
    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @MapsId("patientId")
    @JoinColumn(name = "patient_id", nullable = false)
    private Patient patientId;

    @ManyToMany(fetch = FetchType.EAGER)    // Careful!!!
    @JoinTable(
            name = "prescription_medicine",
            joinColumns = {
                    @JoinColumn(name = "prescription_doctor_id", referencedColumnName = "doctor_id"),
                    @JoinColumn(name = "prescription_patient_id", referencedColumnName = "patient_id")
            },
            inverseJoinColumns = @JoinColumn(name = "medicine_id")
    )
    private List<Medicine> medicines;

    public Prescription() {
    }

    public Prescription(Doctor doctorId, Patient patientId, List<Medicine> medicines) {
        this.id = new PrescriptionId(doctorId.getId(), patientId.getId());  // OBS !!!
        this.doctorId = doctorId;
        this.patientId = patientId;
        this.medicines = medicines;
    }

//    public Long getId() {
//        return id;
//    }
//
//    public void setId(Long id) {
//        this.id = id;
//    }


    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public Doctor getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(Doctor doctor) {
        this.doctorId = doctor;
    }

    public Patient getPatientId() {
        return patientId;
    }

    public void setPatientId(Patient patient) {
        this.patientId = patient;
    }

    public List<Medicine> getMedicines() {
        return medicines;
    }

    public void setMedicines(List<Medicine> medicines) {
        this.medicines = medicines;
    }
}
