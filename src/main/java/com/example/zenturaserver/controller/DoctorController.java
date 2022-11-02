package com.example.zenturaserver.controller;

import com.example.zenturaserver.model.DoctorDTO;
import com.example.zenturaserver.service.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class DoctorController {

    @Autowired
    private DoctorService doctorService;

    // -----    GET      -----
    @GetMapping("/doctor/getall")
    public ResponseEntity<?> getAllDoctors() {
        List<DoctorDTO> doctors = doctorService.getAllDoctors();
        return new ResponseEntity<>(doctors, HttpStatus.OK );
    }

    @GetMapping("/doctor/get/{id}")
    public ResponseEntity<?> getADoctor(@PathVariable("id") String id) {

        try {
            return new ResponseEntity<>(doctorService.getADoctor(id), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }

    }

    // -----    POST      -----
    @PostMapping("/doctor/create")
    public ResponseEntity<?> createDoctor(@RequestBody DoctorDTO doctor) {
        try {
            doctorService.createDoctor(doctor);
            return new ResponseEntity<DoctorDTO>(doctor, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    // -----    UPDATE      -----
    @PutMapping("/doctor/update/{id}")
    public ResponseEntity<?> updateADoctor(@PathVariable("id") String id, @RequestBody DoctorDTO doctor) {

        try {
            doctorService.updateDoctor(id, doctor);
            return new ResponseEntity<>("doctor updated succesfully!", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }

    }

    // -----    DELETE      -----
    @DeleteMapping("/doctor/delete/{id}")
    public ResponseEntity<?> deleteADoctor(@PathVariable("id") String id) {
        try {
            doctorService.deleteDoctor(id);
            return new ResponseEntity<>("doctor deleted successfully!", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
}
