package com.demo.healthcare.demo;

import com.demo.healthcare.model.Address;
import com.demo.healthcare.model.Gender;
import com.demo.healthcare.model.Patient;
import com.demo.healthcare.repository.PatientRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

//@Component
public class EmbeddedDataPopulator implements CommandLineRunner {

    private final PatientRepository patientRepository;

    public EmbeddedDataPopulator(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        Address address = new Address("101 Main St.", "Springfield", "IL", "873643");
        Patient patient = new Patient("John", 30,"johndoe@test.com", Gender.MALE);
        patient.setAddress(address);

        patientRepository.save(patient);
    }
}
