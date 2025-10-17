package com.demo.healthcare.demo;

import com.demo.healthcare.model.Gender;
import com.demo.healthcare.model.Patient;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

//@Component
public class JpaEntityManagerDemoMerge implements CommandLineRunner {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    @Transactional
    public void run(String... args) throws Exception {
        Patient patient = new Patient("John Doe", 30,"johndoe@test.com", Gender.MALE);
        entityManager.persist(patient);
        patient.setName("Jane");

        entityManager.detach(patient);
        System.out.println("Is Managed? " + entityManager.contains(patient));

        Patient mergedPatient = entityManager.merge(patient);
        System.out.println("Is Managed? " + entityManager.contains(mergedPatient));

        patient.setName("Not Managed Name");
        mergedPatient.setName("Managed Name");

        Patient patient2 = new Patient("John Doe 2", 32,"johndoe2@test.com", Gender.MALE);
        entityManager.merge(patient2);

        Patient p = entityManager.find(Patient.class, 1L);
        p.setName("John Doe Updated");
        p.setAge(48);
        entityManager.merge(p);
    }
}
