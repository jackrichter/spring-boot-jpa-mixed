package com.demo.healthcare.transactions;

import com.demo.healthcare.model.Gender;
import com.demo.healthcare.model.MedicalRecord;
import com.demo.healthcare.model.Patient;
import com.demo.healthcare.repository.MedicalRecordRepository;
import com.demo.healthcare.repository.PatientRepository;
import jakarta.transaction.Transactional;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;


@Component
public class WithTransactions implements CommandLineRunner {

    private final PatientRepository patientRepository;
    private final MedicalRecordRepository medicalRecordRepository;

    public WithTransactions(PatientRepository patientRepository, MedicalRecordRepository medicalRecordRepository) {
        this.patientRepository = patientRepository;
        this.medicalRecordRepository = medicalRecordRepository;
    }

    @Override
    @Transactional(rollbackOn = Exception.class)
    public void run(String... args) throws Exception {
        Patient patient = new Patient("John", 30, "john@example.com", Gender.MALE);
        patientRepository.save(patient);

        // Simulate a failure
        if(true) {
            throw new Exception("Simulated failure after saving a patient");
        }

        MedicalRecord record = new MedicalRecord("Hypertension");
        record.setPatient(patient);
        medicalRecordRepository.save(record);

        patient.setMedicalRecord(record);
        patientRepository.save(patient);
    }
}
