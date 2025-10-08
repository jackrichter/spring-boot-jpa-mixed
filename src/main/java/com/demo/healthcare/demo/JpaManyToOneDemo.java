package com.demo.healthcare.demo;

import com.demo.healthcare.model.Doctor;
import com.demo.healthcare.model.Patient;
import com.demo.healthcare.repository.DoctorRepository;
import com.demo.healthcare.repository.PatientRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

//@Component
public class JpaManyToOneDemo implements CommandLineRunner {

    private final PatientRepository patientRepository;
    private final DoctorRepository doctorRepository;

    public JpaManyToOneDemo(PatientRepository patientRepository, DoctorRepository doctorRepository) {
        this.patientRepository = patientRepository;
        this.doctorRepository = doctorRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        Doctor doctor1 = new Doctor("Dr. Alex");
        doctorRepository.save(doctor1);

        Doctor doctor2 = new Doctor("Dr. Alyne");
        doctorRepository.save(doctor2);

        Patient patient1 = new Patient("John Doe", 30);
        patient1.setDoctor(doctor1);
        patientRepository.save(patient1);

        Patient patient2 = new Patient("Jane", 33);
        patient2.setDoctor(doctor1);
        patientRepository.save(patient2);
    }
}
