package com.example.zenturaserver.controller;

import com.example.zenturaserver.model.PatientDTO;
import com.example.zenturaserver.repository.PatientRepository;
import com.example.zenturaserver.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.ConstraintViolationException;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
public class PatientController {

    @Autowired
    private PatientService patientService;

    // -----    GET      -----
    @GetMapping("/patient/getall")
    public ResponseEntity<?> getAllPatients() {
        List<PatientDTO> patients = patientService.getAllPatients();
        return new ResponseEntity<>(patients, patients.size() > 0 ? HttpStatus.OK : HttpStatus.NOT_FOUND);
    }

    @GetMapping("/patient/get/{id}")
    public ResponseEntity<?> getAPatient(@PathVariable("id") String id) {

        try {
            return new ResponseEntity<>(patientService.getAPatient(id), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }

    }

    // -----    POST      -----
    @PostMapping("/patient/create")
    public ResponseEntity<?> createPatient(@RequestBody PatientDTO patient) {
        try {
            patientService.createPatient(patient);
            return new ResponseEntity<PatientDTO>(patient, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    // -----    UPDATE      -----
    @PutMapping("/patient/update/{id}")
    public ResponseEntity<?> updateAPatient(@PathVariable("id") String id, @RequestBody PatientDTO patient) {

        try {
            patientService.updatePatient(id, patient);
            return new ResponseEntity<>("Patient updated succesfully!", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }

    }

    // -----    DELETE      -----
    @DeleteMapping("/patient/delete/{id}")
    public ResponseEntity<?> deleteAPatient(@PathVariable("id") String id) {
        try {
            patientService.deletePatient(id);
            return new ResponseEntity<>("Patient deleted successfully!", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
}
