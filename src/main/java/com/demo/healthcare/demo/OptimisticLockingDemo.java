package com.demo.healthcare.demo;

import com.demo.healthcare.model.Doctor;
import com.demo.healthcare.model.Gender;
import com.demo.healthcare.model.Patient;
import com.demo.healthcare.repository.PatientRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.orm.ObjectOptimisticLockingFailureException;
import org.springframework.stereotype.Component;

//@Component
public class OptimisticLockingDemo implements CommandLineRunner {

    private final PatientRepository patientRepository;

    public OptimisticLockingDemo(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        Doctor drSmith = new Doctor("Dr. Smith");

        Patient alice = new Patient("Alice Johnson", 30,"alicejohnson@test.com", Gender.FEMALE);
        alice.setGender(Gender.FEMALE);
        alice.setDoctor(drSmith);

        patientRepository.save(alice);

        Patient p1 = patientRepository.findById(1L).get();  // p1 -> version 0
        Patient p2 = patientRepository.findById(1L).get();  // p2 (== p1) -> version 0

        p1.setName("New Alice  1");
        patientRepository.save(p1);  // p1 -> current version updated to 1
//        Patient p2 = patientRepository.findById(1L).get();  // IMPORTANT! If the find is moved to here, this would have worked!

        try {
            p2.setName("New Alice  2"); // p2 -> current version is still 0
            patientRepository.save(p2); // p2 data is outdated ==> Exception!
        } catch (ObjectOptimisticLockingFailureException e) {
            System.out.println("----------p2 data is outdated!------------");
            e.printStackTrace();
        }
    }
}
