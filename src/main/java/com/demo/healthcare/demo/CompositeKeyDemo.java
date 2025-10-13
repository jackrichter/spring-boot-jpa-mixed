package com.demo.healthcare.demo;

import com.demo.healthcare.model.*;
import com.demo.healthcare.repository.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import java.nio.file.Files;
import java.util.List;
import java.util.Optional;

@Component
public class CompositeKeyDemo implements CommandLineRunner {

    private final DoctorRepository doctorRepository;
    private final PrescriptionRepository prescriptionRepository;
    private final PatientRepository patientRepository;
    private final MedicineRepository medicineRepository;

    public CompositeKeyDemo(DoctorRepository doctorRepository, PrescriptionRepository prescriptionRepository, PatientRepository patientRepository, MedicineRepository medicineRepository) {
        this.doctorRepository = doctorRepository;
        this.prescriptionRepository = prescriptionRepository;
        this.patientRepository = patientRepository;
        this.medicineRepository = medicineRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        Doctor drSmith = new Doctor("Dr. Smith");

        Patient alice = new Patient("Alice Johnson", 30);
        Address address = new Address("101 Main St.", "Springfield", "IL", "873643");
        alice.setAddress(address);
        byte[] imageData = Files.readAllBytes(new ClassPathResource("/images/img.png").getFile().toPath());
        alice.setProfilePicture(imageData);
        alice.setGender(Gender.FEMALE);
        alice.setDoctor(drSmith);

        MedicalRecord aliceRecord = new MedicalRecord("Allergic to penicillin");
        alice.setMedicalRecord(aliceRecord);
        patientRepository.save(alice);

        Medicine m1 = new Medicine("Paracetamol");
        Medicine m2 = new Medicine("Ibuprofen");
        Medicine m3 = new Medicine("Amoxicillin");
        medicineRepository.saveAll(List.of(m1, m2, m3));

        Prescription prescription = new Prescription(drSmith, alice, List.of(m1, m2));
        prescription.setNotes("Alice's prescription");
        prescriptionRepository.save(prescription);

        // FETCHING PRESCRIPTION WITH COMPOSITE KEY
        PrescriptionId key = new PrescriptionId(drSmith.getId(), alice.getId());
        Optional<Prescription> fetched = prescriptionRepository.findById(key);

        if (fetched.isPresent()) {
            Prescription p = fetched.get();
            System.out.println("Prescription found by Dr. : " + p.getDoctorId().getName() +
                    " -> Patient: " + p.getPatientId().getName());
            System.out.println("Medicines Prescribed:");
            p.getMedicines().forEach(medicine -> {
                System.out.println(medicine.getName());
            });
        }
    }
}
