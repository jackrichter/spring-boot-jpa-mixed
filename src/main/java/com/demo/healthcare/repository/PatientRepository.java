package com.demo.healthcare.repository;

import com.demo.healthcare.model.Gender;
import com.demo.healthcare.model.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PatientRepository extends JpaRepository<Patient, Long> {

//    List<Patient> findByName(String name);      // Spring Data. Without the help of JPQL.

    // With the help of JPQL, using NAMED
//    @Query("select p from Patient p where p.name = :name and p.gender = :gender")
//    List<Patient> findByNameAndGender(@Param("name") String name, @Param("gender") Gender gender);

    // Using POSITIONAL
    @Query("select p from Patient p where p.name = ?1 and p.gender = ?2")
    List<Patient> findByNameAndGender(String name, Gender gender);
}
