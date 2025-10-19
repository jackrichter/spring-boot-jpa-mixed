package com.demo.healthcare.demo.jpql;

import com.demo.healthcare.model.Gender;
import com.demo.healthcare.model.Patient;
import com.demo.healthcare.repository.PatientRepository;
import jakarta.persistence.EntityManager;

import java.util.List;

public class QueryParameters {

    private final PatientRepository patientRepository;

    public QueryParameters(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    public void execute(EntityManager entityManager) {

        // NAMED
//        TypedQuery<Patient> query = entityManager.createQuery(
//                "select p from Patient p where p.name = :name",
//                Patient.class
//        );
//        query.setParameter("name", "John Doe");
//        System.out.println("SIZE: " + query.getResultList().size());

        // POSITIONAL
//        TypedQuery<Patient> query = entityManager.createQuery(
//                "select p from Patient p where p.name = ?1" +
//                " and p.gender = ?2",
//                Patient.class
//        );
//        query.setParameter(1, "John Doe");
//        query.setParameter(2, Gender.MALE);
//        System.out.println("SIZE: " + query.getResultList().size());

        // USING REPOSITORIES
        List<Patient> p = patientRepository.findByNameAndGender("Alice", Gender.FEMALE);
        System.out.println("SIZE of p: " + p.size());   // -> 1
        List<Patient> p1 = patientRepository.findByName("John");   // -> 0 (Obs!)
        System.out.println("SIZE of p1: " + p1.size());
        List<Patient> p2 = patientRepository.findByName("John Doe");
        System.out.println("SIZE of p2: " + p2.size());   // -> 1
        Patient p3 = patientRepository.findByDoctorName("Dr. Smith");
        System.out.println("Patient found by doctorName: " + (p3 != null ? true : false));  // -> true
        System.out.println("Exists by email: " + patientRepository.existsByEmail("john111@email.com"));  // -> false // -> true
        System.out.println("Count by age: " + patientRepository.countByAge(30));  // -> 1
    }
}
