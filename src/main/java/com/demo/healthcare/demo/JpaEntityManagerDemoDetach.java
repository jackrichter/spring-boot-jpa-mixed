package com.demo.healthcare.demo;

import com.demo.healthcare.model.Gender;
import com.demo.healthcare.model.Patient;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

//@Component
public class JpaEntityManagerDemoDetach implements CommandLineRunner {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    @Transactional
    public void run(String... args) throws Exception {
        Patient patient = new Patient("John Doe 2", 30,"johndoe2@test.com", Gender.MALE);
        entityManager.persist(patient);
        System.out.println("Is managed: " + entityManager.contains(patient));

        // Detaching = No tracking
        entityManager.detach(patient);
        System.out.println("Is managed [After detached]: " + entityManager.contains(patient));

        patient.setName("Updated");
        patient.setAge(95);
    }
}
