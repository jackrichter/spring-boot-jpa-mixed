package com.demo.healthcare.demo;

import com.demo.healthcare.model.Patient;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

//@Component
public class JpaEntityManagerDemoFind implements CommandLineRunner {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    @Transactional
    public void run(String... args) throws Exception {
        Patient john = new Patient("John Doe", 30);
        entityManager.persist(john);

        Patient jane = new Patient("Jane", 20);
        entityManager.persist(jane);

        Patient patientJohn = entityManager.find(Patient.class, 1L);
        System.out.println("patient John: " + patientJohn.getId());

        Patient patientJane = entityManager.find(Patient.class, 2L);
        System.out.println("patient Jane: " + patientJane.getId());

        Patient marie = new Patient("Marie", 28);
        entityManager.persist(marie);

        // Reference. For lazy fetching
        Patient patientMarieProxy = entityManager.getReference(Patient.class, 3L);
        System.out.println("Got Proxy");
        System.out.println(patientMarieProxy.getName());
    }
}
