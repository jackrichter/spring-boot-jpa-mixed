package com.demo.healthcare.demo.jpql;

import com.demo.healthcare.model.Gender;
import com.demo.healthcare.model.Patient;
import com.demo.healthcare.repository.DoctorRepository;
import com.demo.healthcare.repository.PatientRepository;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class LearnQueries {

    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    private DoctorRepository doctorRepository;

    public  void  execute(EntityManager entityManager){

        // Conditional Queries with Pattern Matching
        List<Patient> older = patientRepository.findOlderThan(30);
        older.forEach(p -> System.out.println("Older than 30: " + p.getName()));

        List<Patient> nameMatch = patientRepository.findByNameNotStartingWith("s");
        nameMatch.forEach(p -> System.out.println("Names matching search pattern: " + p.getName()));

        List<Patient> genderMatch = patientRepository.findByGenders(List.of(Gender.FEMALE, Gender.OTHER));
        genderMatch.forEach(p -> System.out.println("Gender match: " + p.getName()));

        List<Patient> ageRange = patientRepository.findByAgeRange(32, 40);
        ageRange.forEach(p -> System.out.println("Age range match: " + p.getName()));

        List<Patient> unassignedDoctor = patientRepository.findUnassignedDoctorForPatient();
        unassignedDoctor.forEach(p -> System.out.println("Patients with unassigned doctor: " + p.getName()));

        List<Patient> assignedDoctor = patientRepository.findPatientsWithAssignedDoctor();
        assignedDoctor.forEach(p -> System.out.println("Patients with assigned doctor: " + p.getName()));
    }
}
