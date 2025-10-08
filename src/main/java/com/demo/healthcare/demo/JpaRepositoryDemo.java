package com.demo.healthcare.demo;

import com.demo.healthcare.model.Patient;
import com.demo.healthcare.repository.PatientRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Optional;

//@Component
public class JpaRepositoryDemo implements CommandLineRunner {

    private final PatientRepository patientRepository;

    public JpaRepositoryDemo(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        Patient p1 = new Patient("John Doe Repository", 30);
        patientRepository.save(p1);

        Patient p2 = new Patient("Jane", 40);
        patientRepository.save(p2);

        patientRepository.findAll()
                .forEach(e -> System.out.println("Patient: " + e.getName()));

        Optional<Patient> patient = patientRepository.findById(Long.valueOf(2));
        System.out.println("Patient ID 2: " + patient.get().getName());

        // Update Data
        Patient patientIdOne = patientRepository.findById(Long.valueOf(1)).get();
        patientIdOne.setName("John Doe Updated");
        patientRepository.save(patientIdOne);

        // Delete Data
        Patient patientIdTwo = patientRepository.findById(Long.valueOf(2)).get();
        patientRepository.delete(patientIdTwo);

        // Check if Exists
        boolean exists = patientRepository.existsById(1L);
        System.out.println("Patient ID 1 exists: " + exists);
    }
}
