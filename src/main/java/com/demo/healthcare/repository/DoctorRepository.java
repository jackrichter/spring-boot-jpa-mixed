package com.demo.healthcare.repository;

import com.demo.healthcare.model.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DoctorRepository extends JpaRepository<Doctor, Long> {

    // AGGREGATION
    /*
        COUNT() - Total number of rows
        SUM() - Sum of numeric values
        AVG() - Average value
        MAX() - Maximum value
        MIN() - Minimum value
     */
    @Query("select d.specialization, count(p) from Doctor d join d.patients p " +
            "where d.specialization = ?1 order by d.specialization")
    List<Object[]> countOfPatientsByDoctorSpecialization(String specialization);
}
