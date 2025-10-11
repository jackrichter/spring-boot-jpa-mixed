package com.demo.healthcare.demo;

import com.demo.healthcare.model.*;
import com.demo.healthcare.repository.DoctorRepository;
import com.demo.healthcare.repository.MedicineRepository;
import com.demo.healthcare.repository.PatientRepository;
import com.demo.healthcare.repository.PrescriptionRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

//@Component
public class JpaManyToManyDemo implements CommandLineRunner {

    private final MedicineRepository medicineRepository;
    private final PrescriptionRepository prescriptionRepository;
    private final DoctorRepository doctorRepository;
    private final PatientRepository patientRepository;

    public JpaManyToManyDemo(MedicineRepository medicineRepository, PrescriptionRepository prescriptionRepository, DoctorRepository doctorRepository, PatientRepository patientRepository) {
        this.medicineRepository = medicineRepository;
        this.prescriptionRepository = prescriptionRepository;
        this.doctorRepository = doctorRepository;
        this.patientRepository = patientRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        Medicine m1 = new Medicine("Paracetamol");
        Medicine m2 = new Medicine("Ibuprofen");
        Medicine m3 = new Medicine("Amoxicillin");

        medicineRepository.saveAll(List.of(m1, m2, m3));

        Prescription p1 = new Prescription();
        p1.setNotes("Prescription 1");
        p1.setMedicines(List.of(m1, m3));

        Prescription p2 = new Prescription();
        p2.setNotes("Prescription 2");
        p2.setMedicines(List.of(m2, m1, m3));

        prescriptionRepository.saveAll(List.of(p1, p2));

        System.out.println("=== FetchType Demo ===");
//        prescriptionRepository.findById(1L);       // Default Fetch for ManyToMany is Lazy, but we are trying with Eager (Careful!)
    }
}
