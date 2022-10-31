package com.example.zenturaserver.controller;

import com.example.zenturaserver.model.PatientDTO;
import com.example.zenturaserver.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
public class PatientController {

    @Autowired
    private PatientRepository patientRepo;

    // -----    GET      -----
    @GetMapping("/patient/getall")
    public ResponseEntity<?> getAllPatients() {
        List<PatientDTO> patients = patientRepo.findAll();

        if (patients.size() > 0) {
            return new ResponseEntity<List<PatientDTO>>(patients, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("No patients found!", HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/patient/get/{id}")
    public ResponseEntity<?> getAPatient(@PathVariable("id") String id) {

        Optional<PatientDTO> patientOptional = patientRepo.findById(id);

        if (patientOptional.isPresent()) {
            return new ResponseEntity<PatientDTO>(patientOptional.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Patient not found!", HttpStatus.NOT_FOUND);
        }

    }

    // -----    POST      -----
    @PostMapping("/patient/create")
    public ResponseEntity<?> createPatient(@RequestBody PatientDTO patient) {
        try {
            patient.setCreatedAt(new Date(System.currentTimeMillis()));
            patient.setUpdatedAt(new Date(System.currentTimeMillis()));
            patientRepo.save(patient);
            return new ResponseEntity<PatientDTO>(patient, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    // -----    UPDATE      -----
    @PutMapping("/patient/update/{id}")
    public ResponseEntity<?> updateAPatient(@PathVariable("id") String id, @RequestBody PatientDTO patient) {

        Optional<PatientDTO> patientOptional = patientRepo.findById(id);

        if (patientOptional.isPresent()) {
            PatientDTO patientToSave = patientOptional.get();
            patientToSave.setUpdatedAt(new Date(System.currentTimeMillis()));

            //First Name
            patientToSave.setFirstName(patient.getFirstName() != null ? patient.getFirstName() : patientToSave.getFirstName());
            //Last Name
            patientToSave.setLastName(patient.getLastName() != null ? patient.getLastName() : patientToSave.getLastName());
            //Date of Birth
            patientToSave.setDateOfBirth(patient.getDateOfBirth() != null ? patient.getDateOfBirth() : patientToSave.getDateOfBirth());
            //Address
            patientToSave.setAddress(patient.getAddress() != null ? patient.getAddress() : patientToSave.getAddress());
            //Mobile
            patientToSave.setMobile(patient.getMobile() != null ? patient.getMobile() : patientToSave.getMobile());

            patientRepo.save(patientToSave);
            return new ResponseEntity<PatientDTO>(patientToSave, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Patient not found!", HttpStatus.NOT_FOUND);
        }

    }

    // -----    DELETE      -----
    @DeleteMapping("/patient/delete/{id}")
    public ResponseEntity<?> deleteAPatient(@PathVariable("id") String id) {
        try {
           patientRepo.deleteById(id);
            return new ResponseEntity<>("Patient successfully deleted!", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
