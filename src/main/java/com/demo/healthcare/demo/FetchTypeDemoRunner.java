package com.demo.healthcare.demo;

import com.demo.healthcare.model.Doctor;
import com.demo.healthcare.model.Gender;
import com.demo.healthcare.model.MedicalRecord;
import com.demo.healthcare.model.Patient;
import com.demo.healthcare.repository.DoctorRepository;
import com.demo.healthcare.repository.PatientRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

//@Component
public class FetchTypeDemoRunner implements CommandLineRunner {

    private final DoctorRepository doctorRepository;
    private final PatientRepository patientRepository;

    public FetchTypeDemoRunner(DoctorRepository doctorRepository, PatientRepository patientRepository) {
        this.doctorRepository = doctorRepository;
        this.patientRepository = patientRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println("=== STEP 1: Insert Sample Data ===");

        Doctor doctor = new Doctor("Dr. Smith");

        Patient p1 = new Patient("John", 30,"johndoe@test.com", Gender.MALE);
        Patient p2 = new Patient("Alice", 25,"alice@test.com", Gender.FEMALE);
        p1.setDoctor(doctor);
        p2.setDoctor(doctor);

        p1.setMedicalRecord(new MedicalRecord("Allergy to penicillin"));
        p2.setMedicalRecord(new MedicalRecord("History of asthma"));

        patientRepository.saveAll(List.of(p1, p2));

        System.out.println("=== STEP 2: FetchType Demo ===");

        /**
         * Defaults:
         * -> OneToOne: Eager
         * -> OneToMany: Lazy
         * -> ManyToOne: Eager
         * -> ManyToMany: Lazy
         */

        patientRepository.findById(1L).orElseThrow();   // Eager because the relations to Doctor and MedicalRecord

        doctorRepository.findById(1L).orElseThrow();    // Lazy as Doctor and Patient is OneToMany (only Doctor is fetched, not Patient!)
    }
}
