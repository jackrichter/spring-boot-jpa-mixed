package com.demo.healthcare.demo;

import com.demo.healthcare.model.Doctor;
import com.demo.healthcare.model.Gender;
import com.demo.healthcare.model.Patient;
import com.demo.healthcare.repository.DoctorRepository;
import com.demo.healthcare.repository.PatientRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

//@Component
public class JpaOneToManyBiDirectionalDemo implements CommandLineRunner {

    private final PatientRepository patientRepository;
    private final DoctorRepository doctorRepository;

    public JpaOneToManyBiDirectionalDemo(PatientRepository patientRepository, DoctorRepository doctorRepository) {
        this.patientRepository = patientRepository;
        this.doctorRepository = doctorRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        Doctor doctor1 = new Doctor("Dr. Alex");
        doctorRepository.save(doctor1);

        Doctor doctor2 = new Doctor("Dr. Alyne");
        doctorRepository.save(doctor2);

        Patient patient1 = new Patient("John Doe", 30,"johndoe@test.com", Gender.MALE);
        patient1.setDoctor(doctor1);
        patientRepository.save(patient1);

        Patient patient2 = new Patient("Jane", 33,"johndoe@test.com", Gender.FEMALE);
        patient2.setDoctor(doctor1);
        patientRepository.save(patient2);

        doctor1.setPatients(List.of(patient1, patient2));

        System.out.println(patient1.getDoctor().getName());
        System.out.println(doctor1.getPatients().get(0).getName());

    }
}
