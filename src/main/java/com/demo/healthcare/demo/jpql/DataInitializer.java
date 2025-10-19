package com.demo.healthcare.demo.jpql;

import com.demo.healthcare.model.Doctor;
import com.demo.healthcare.model.Gender;
import com.demo.healthcare.model.MedicalRecord;
import com.demo.healthcare.model.Patient;
import com.demo.healthcare.repository.DoctorRepository;
import com.demo.healthcare.repository.MedicalRecordRepository;
import com.demo.healthcare.repository.PatientRepository;
import com.demo.healthcare.repository.PersonRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer implements CommandLineRunner {

    private final DoctorRepository doctorRepository;
    private final PatientRepository patientRepository;
    private final PersonRepository personRepository;
    private final MedicalRecordRepository medicalRecordRepository;

    @PersistenceContext
    private EntityManager entityManager;

    public DataInitializer(DoctorRepository doctorRepository, PatientRepository patientRepository, PersonRepository personRepository, MedicalRecordRepository medicalRecordRepository) {
        this.doctorRepository = doctorRepository;
        this.patientRepository = patientRepository;
        this.personRepository = personRepository;
        this.medicalRecordRepository = medicalRecordRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        Doctor doctor = new Doctor("Dr. Smith", 45, "smith@hospital.com", "Cardiology");
        doctorRepository.save(doctor);

        Patient johnDoe = new Patient("John Doe", 30, "john@email.com", Gender.MALE);
        patientRepository.save(johnDoe);

        johnDoe.setDoctor(doctor);

        MedicalRecord record = new MedicalRecord();
        record.setPatient(johnDoe);
        record.setDiagnosis("Hypertension");
        medicalRecordRepository.save(record);

        johnDoe.setMedicalRecord(record);
        patientRepository.save(johnDoe);

        Patient alice = new Patient("Alice", 38, "alice@email.com", Gender.FEMALE);
        patientRepository.save(alice);

        QueryParameters queryParameters = new QueryParameters(patientRepository);
        queryParameters.execute(entityManager);
    }
}
