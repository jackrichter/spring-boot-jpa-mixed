package com.demo.healthcare.demo;

import com.demo.healthcare.model.Doctor;
import com.demo.healthcare.model.Patient;
import com.demo.healthcare.repository.DoctorRepository;
import com.demo.healthcare.repository.PatientRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

//@Component
public class CascadingDemo implements CommandLineRunner {

    private final PatientRepository patientRepository;
    private final DoctorRepository doctorRepository;

    public CascadingDemo(PatientRepository patientRepository, DoctorRepository doctorRepository) {
        this.patientRepository = patientRepository;
        this.doctorRepository = doctorRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        /**
         * CascadeType.PERSIST
         */
        Doctor doctor1 = new Doctor("Dr. Alex");
//        doctorRepository.save(doctor1);

        Doctor doctor2 = new Doctor("Dr. Alyne");
//        doctorRepository.save(doctor2);         // Needed because no patient assigned to doctor2, thus no cascading effect

        Patient patient1 = new Patient("John Doe", 30);
        patient1.setDoctor(doctor1);
//        patientRepository.save(patient1);

        Patient patient2 = new Patient("Jane", 33);
        patient2.setDoctor(doctor1);        // Doctor1 gets detached after the save(patient1) causing an exception
//        patientRepository.save(patient2);

        // Solution to the detachment problem
        patientRepository.saveAll(List.of(patient1, patient2));

        doctor1.setPatients(List.of(patient1, patient2));

        System.out.println(patient1.getDoctor().getName());
        System.out.println(doctor1.getPatients().get(0).getName());

        /**
         * CascadeType.REMOVE
         */
//        Doctor doctor = doctorRepository.findById(doctor1.getId()).get();
//        doctorRepository.delete(doctor);    // Removes both patients from db as well!! Be Careful

        /**
         * CascadeType.MERGE
         */
        System.out.println("=========== CascadeType.MERGE ==========");
        Doctor managedDoctor = doctorRepository.findById(1L).orElseThrow();
        managedDoctor.setName(managedDoctor.getName() + " Updated.");

        Patient managedPatient = patientRepository.findById(1L).orElseThrow();
        managedPatient.setAge(44);

        managedDoctor.setPatients(List.of(managedPatient));
        doctorRepository.save(managedDoctor);   // Both updates should be reflected in db
    }
}
