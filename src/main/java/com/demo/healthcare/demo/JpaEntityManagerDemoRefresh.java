package com.demo.healthcare.demo;

import com.demo.healthcare.model.Gender;
import com.demo.healthcare.model.Patient;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

//@Component
public class JpaEntityManagerDemoRefresh implements CommandLineRunner {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    @Transactional
    public void run(String... args) throws Exception {
        Patient patient = new Patient("John Doe", 30,"johndoe@test.com", Gender.MALE);
        entityManager.persist(patient);

       patient.setName("Update 1");     // Does not update Name
       entityManager.refresh(patient);  // Database state is set on patient

       patient.setAge(155);             // Does update age
    }
}
