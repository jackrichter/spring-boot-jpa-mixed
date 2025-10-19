package com.demo.healthcare.demo.jpql;

import com.demo.healthcare.model.Gender;
import com.demo.healthcare.model.Patient;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;

public class QueryParameters {

    public void execute(EntityManager entityManager) {

        // NAMED
//        TypedQuery<Patient> query = entityManager.createQuery(
//                "select p from Patient p where p.name = :name",
//                Patient.class
//        );
//        query.setParameter("name", "John Doe");
//        System.out.println("SIZE: " + query.getResultList().size());

        // POSITIONAL
        TypedQuery<Patient> query = entityManager.createQuery(
                "select p from Patient p where p.name = ?1" +
                " and p.gender = ?2",
                Patient.class
        );
        query.setParameter(1, "John Doe");
        query.setParameter(2, Gender.MALE);
        System.out.println("SIZE: " + query.getResultList().size());
    }
}
