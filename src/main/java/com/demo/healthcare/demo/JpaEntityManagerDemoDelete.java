package com.demo.healthcare.demo;

import com.demo.healthcare.model.Patient;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

//@Component
public class JpaEntityManagerDemoDelete implements CommandLineRunner {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    @Transactional
    public void run(String... args) throws Exception {
        Patient patient = new Patient("John Doe", 30);
        entityManager.persist(patient);

        entityManager.remove(patient);      // For Managed Entities only!
    }
}
