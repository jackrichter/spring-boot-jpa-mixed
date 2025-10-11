package com.demo.healthcare.model;

import jakarta.persistence.Embeddable;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class PrescriptionId implements Serializable {

    private Long doctorId;
    private Long patientId;

    public PrescriptionId() {
    }

    public PrescriptionId(Long doctorId, Long patientId) {
        this.doctorId = doctorId;
        this.patientId = patientId;
    }

    public Long getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(Long doctorId) {
        this.doctorId = doctorId;
    }

    public Long getPatientId() {
        return patientId;
    }

    public void setPatientId(Long patientId) {
        this.patientId = patientId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(doctorId, patientId);
    }

    @Override
    public boolean equals(Object obj) {
        if  (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        PrescriptionId other = (PrescriptionId) obj;
        return Objects.equals(doctorId, other.doctorId) && Objects.equals(patientId, other.patientId);
    }
}
