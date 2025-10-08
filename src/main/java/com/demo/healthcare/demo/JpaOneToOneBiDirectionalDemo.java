package com.demo.healthcare.demo;

import com.demo.healthcare.model.MedicalRecord;
import com.demo.healthcare.model.Patient;
import com.demo.healthcare.repository.MedicalRecordRepository;
import com.demo.healthcare.repository.PatientRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

//@Component
public class JpaOneToOneBiDirectionalDemo implements CommandLineRunner {

    private final PatientRepository patientRepository;
    private final MedicalRecordRepository medicalRecordRepository;

    public JpaOneToOneBiDirectionalDemo(PatientRepository patientRepository, MedicalRecordRepository medicalRecordRepository) {
        this.patientRepository = patientRepository;
        this.medicalRecordRepository = medicalRecordRepository;
    }

    @Override
    public void run(String... args) throws Exception {
//        MedicalRecord medicalRecord = new MedicalRecord("Fever");
//        medicalRecordRepository.save(medicalRecord);
//
//        Patient patient = new Patient("John Doe", 30);
//        patient.setMedicalRecord(medicalRecord);
//        patientRepository.save(patient);
//
//        medicalRecord.setPatient(patient);
//        medicalRecordRepository.save(medicalRecord);    // Update
//
//        System.out.println("Patient's diagnosis: " + patient.getMedicalRecord().getDiagnosis());
//        System.out.println("Patient with the diagnosis: " + medicalRecord.getPatient().getName());

        MedicalRecord medicalRecord = new MedicalRecord("Fever");
        medicalRecordRepository.save(medicalRecord);

        Patient patient = new Patient("John Doe", 30);
        patient.setMedicalRecord(medicalRecord);
        patientRepository.save(patient);

        // While fetching Hibernate populates both sides of the relationship as both Entities are annotated
        MedicalRecord fetched = medicalRecordRepository.findById(medicalRecord.getId()).orElseThrow();

        System.out.println("Patient's diagnosis: " + patient.getMedicalRecord().getDiagnosis());
        System.out.println("Patient with the diagnosis: " + fetched.getPatient().getName());

    }
}
