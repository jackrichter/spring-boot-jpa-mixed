package com.demo.healthcare.repository;

import com.demo.healthcare.model.Prescription;
import com.demo.healthcare.model.PrescriptionId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PrescriptionRepository extends JpaRepository<Prescription, PrescriptionId> {
}
