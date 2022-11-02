package com.example.zenturaserver.controller;

import com.example.zenturaserver.model.LabDTO;
import com.example.zenturaserver.service.LabService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class LabController {

    @Autowired
    private LabService labService;

    // -----    GET      -----
    @GetMapping("/lab/getall")
    public ResponseEntity<?> getAllPatients() {
        List<LabDTO> labReports = labService.getAllLabReports();
        return new ResponseEntity<>(labReports, HttpStatus.OK);
    }

    @GetMapping("/lab/get/{id}")
    public ResponseEntity<?> getALabReport(@PathVariable("id") String id) {
        try {
            return new ResponseEntity<>(labService.getALabReport(id), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    // -----    POST      -----
    @PostMapping("/lab/create")
    public ResponseEntity<?> createLabReport(@RequestBody LabDTO labReport) {
        try {
            labService.createLabReport(labReport);
            return new ResponseEntity<LabDTO>(labReport, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // -----    UPDATE      -----
    @PutMapping("/lab/update/{id}")
    public ResponseEntity<?> updateAPatient(@PathVariable("id") String id, @RequestBody LabDTO labReport) {

        try {
            labService.updateLabReport(id, labReport);
            return new ResponseEntity<>("Lab report updated successfully!", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }

    }

    // -----    DELETE      -----
    @DeleteMapping("/lab/delete/{id}")
    public ResponseEntity<?> deleteAPatient(@PathVariable("id") String id) {
        try {
            labService.deleteLabReport(id);
            return new ResponseEntity<>("Lab report deleted successfully!", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
}
