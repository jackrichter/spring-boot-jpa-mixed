package com.demo.healthcare.demo;

import com.demo.healthcare.model.Gender;
import com.demo.healthcare.model.Patient;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

//@Component
public class JpaEntityManagerDemo implements CommandLineRunner {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    @Transactional
    public void run(String... args) throws Exception {
        Patient patient = new Patient("John Doe", 30,"johndoe@test.com", Gender.MALE);
        entityManager.persist(patient);

        System.out.println("Persisted Patient: " + patient);

        // The managed Entity is tracked for changes
        patient.setName("John Doe Tracked");
        System.out.println("Tracked Patient: " + patient);

        // Detaching = No tracking
    }
}
