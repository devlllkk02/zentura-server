package com.example.zenturaserver.controller;


import com.example.zenturaserver.model.PharmacyDTO;
import com.example.zenturaserver.repository.PharmacyRepository;
import com.example.zenturaserver.service.PharmacyService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
public class PharmacyController {

    @Autowired
    private PharmacyRepository pharmacyRepo;

    @Autowired
    private PharmacyService pharmacyService;

    // -----    GET      -----
    @GetMapping("/pharmacy/getall")
    public ResponseEntity<?> getAllPharmacies() {
        List<PharmacyDTO> pharmacies = pharmacyService.getAllPharmacy();
        return new ResponseEntity<>(pharmacies, pharmacies.size() > 0 ? HttpStatus.OK : HttpStatus.NOT_FOUND);
    }

    @GetMapping("/pharmacy/get/{id}")
    public ResponseEntity<?> getAPharmacy(@PathVariable("id") String id) {

        try {
            return new ResponseEntity<>(pharmacyService.getAPharmacy(id), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }

    }

    // -----    POST      -----
    @PostMapping("/pharmacy/create")
    public ResponseEntity<?> createPharmacy(@RequestBody PharmacyDTO pharmacy) {
        try {
            pharmacyService.createPharmacy(pharmacy);
            return new ResponseEntity<PharmacyDTO>(pharmacy, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    // -----    UPDATE      -----
    @PutMapping("/pharmacy/update/{id}")
    public ResponseEntity<?> updateAPharmacy(@PathVariable("id") String id, @RequestBody PharmacyDTO pharmacy) {

        try {
            pharmacyService.updatePharmacy(id, pharmacy);
            return new ResponseEntity<>("Pharmacy updated succesfully!", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }

    }

    // -----    DELETE      -----
    @DeleteMapping("/pharmacy/delete/{id}")
    public ResponseEntity<?> deleteAPharmacy(@PathVariable("id") String id) {
        try {
            pharmacyService.deletePharmacy(id);
            return new ResponseEntity<>("Pharmacy deleted successfully!", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
}
