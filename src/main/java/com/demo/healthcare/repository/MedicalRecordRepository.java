package com.demo.healthcare.repository;

import com.demo.healthcare.model.MedicalRecord;
import com.demo.healthcare.model.Patient;
import org.apache.el.lang.ELArithmetic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MedicalRecordRepository extends JpaRepository<MedicalRecord, Long> {
}
