package com.demo.healthcare.demo;

import com.demo.healthcare.model.Doctor;
import com.demo.healthcare.model.Gender;
import com.demo.healthcare.model.Patient;
import com.demo.healthcare.model.Person;
import com.demo.healthcare.repository.DoctorRepository;
import com.demo.healthcare.repository.PatientRepository;
import com.demo.healthcare.repository.PersonRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class InheritanceDemo implements CommandLineRunner {

    private final PersonRepository personRepository;
    private final DoctorRepository doctorRepository;
    private final PatientRepository patientRepository;

    public InheritanceDemo(PersonRepository personRepository, DoctorRepository doctorRepository, PatientRepository patientRepository) {
        this.personRepository = personRepository;
        this.doctorRepository = doctorRepository;
        this.patientRepository = patientRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        Doctor doctor = new Doctor("Dr. Smith", 45, "smith@hospital.com", "Cardiology");
        doctorRepository.save(doctor);

        Patient patient = new Patient("John Doe", 30, "john@email.com", Gender.MALE);
        patientRepository.save(patient);

        // Query all Persons (Polymorphic Query)
        List<Person> people = personRepository.findAll();
        System.out.println("All persons:");
        people.forEach(person -> System.out.println(person.getName() +
                " (" + person.getClass().getSimpleName() + ")"));

        // Query Child-classes
        Patient fetchedPatient = patientRepository.findById(patient.getId()).get();
        System.out.println("Patient 1: " + fetchedPatient.getName());

        Doctor fetchedDoctor = doctorRepository.findById(doctor.getId()).get();
        System.out.println("Doctor 1: " + fetchedDoctor.getName());
    }
}
