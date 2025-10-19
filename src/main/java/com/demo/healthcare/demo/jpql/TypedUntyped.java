package com.demo.healthcare.demo.jpql;

import com.demo.healthcare.model.Patient;
import com.demo.healthcare.repository.DoctorRepository;
import com.demo.healthcare.repository.MedicalRecordRepository;
import com.demo.healthcare.repository.PatientRepository;
import com.demo.healthcare.repository.PersonRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

//@Component
public class TypedUntyped implements CommandLineRunner {

    private final DoctorRepository doctorRepository;
    private final PatientRepository patientRepository;
    private final PersonRepository personRepository;
    private final MedicalRecordRepository medicalRecordRepository;

    @PersistenceContext
    private EntityManager entityManager;

    public TypedUntyped(DoctorRepository doctorRepository, PatientRepository patientRepository, PersonRepository personRepository, MedicalRecordRepository medicalRecordRepository) {
        this.doctorRepository = doctorRepository;
        this.patientRepository = patientRepository;
        this.personRepository = personRepository;
        this.medicalRecordRepository = medicalRecordRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        // TYPED
        TypedQuery<Patient> typedQuery = entityManager.createQuery(
                "select p from Patient p", Patient.class
        );
        List<Patient> patients = typedQuery.getResultList();
        System.out.println("Patients count: " + patients.size());

        // UNTYPED
        Query query = entityManager.createQuery(
                "select p from Patient p");
        List<Patient> patientList = query.getResultList();
        System.out.println("PatientList count: " + patientList.size());
    }
}
