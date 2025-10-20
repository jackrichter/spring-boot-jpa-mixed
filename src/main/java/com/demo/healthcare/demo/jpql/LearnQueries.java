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

        // Sorting Results
        List<Patient> sortBy = patientRepository.sortByAgeAsc();
        sortBy.forEach(p -> System.out.println("Sorted Data: " + p.getName()));

        List<Patient> sortByDesc = patientRepository.sortByAgeDescAndNameAsc();
        sortByDesc.forEach(p ->
                System.out.println("Sorted Data, age desc and name asc: " + p.getName() + ", " + p.getAge()));

        List<Patient> sortAndFilter = patientRepository.findByGenderSortByAgeDesc(Gender.MALE);
        sortAndFilter.forEach(p ->
                System.out.println("Sorted and Filtered Data : " + p.getName() + ", " + p.getAge()));

        // INNER JOIN
        List<Patient> innerJoinPatients = patientRepository.findPatientsWithADoctor("Cardiology");
        innerJoinPatients.forEach(p -> System.out.println("Inner Join Data: " + p.getName()));

        // LEFT JOIN
        List<Patient> leftJoin = patientRepository.findPatientsWithOrWithoutAssignedDoctor();
        leftJoin.forEach(p -> System.out.println("Left Join Data: " + p.getName()));

        // JOIN FETCH (will include Doctor data!)
        List<Patient> joinFetch = patientRepository.findPatientsWithADoctorJoinFetch();
        joinFetch
                .forEach(p -> System.out.println("Join Fetch Data: " + p.getName() + " " + p.getDoctor().getName()));
    }
}
