package com.example.zenturaserver.controller;

import com.example.zenturaserver.model.LabDTO;
import com.example.zenturaserver.model.PharmacyDTO;
import com.example.zenturaserver.service.LabService;
import com.example.zenturaserver.service.PharmacyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PharmacyController {

    @Autowired
    private PharmacyService pharmacyService;

    // -----    GET      -----
    @GetMapping("/pharmacy/getall")
    public ResponseEntity<?> getAllPatients() {
        List<PharmacyDTO> invoices = pharmacyService.getAllInvoices();
        return new ResponseEntity<>(invoices, invoices.size() > 0 ? HttpStatus.OK : HttpStatus.NOT_FOUND);
    }

    @GetMapping("/pharmacy/get/{id}")
    public ResponseEntity<?> getAnInvoice(@PathVariable("id") String id) {
        try {
            return new ResponseEntity<>(pharmacyService.getAnInvoice(id), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    // -----    POST      -----
    @PostMapping("/pharmacy/create")
    public ResponseEntity<?> createInvoice(@RequestBody PharmacyDTO invoice) {
        try {
            pharmacyService.createInvoice(invoice);
            return new ResponseEntity<PharmacyDTO>(invoice, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // -----    UPDATE      -----
    @PutMapping("/pharmacy/update/{id}")
    public ResponseEntity<?> updateInvoice(@PathVariable("id") String id, @RequestBody PharmacyDTO invoice) {

        try {
            pharmacyService.updateInvoice(id, invoice);
            return new ResponseEntity<>("Invoice updated successfully!", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }

    }

    // -----    DELETE      -----
    @DeleteMapping("/pharmacy/delete/{id}")
    public ResponseEntity<?> deleteInvoice(@PathVariable("id") String id) {
        try {
            pharmacyService.deleteInvoice(id);
            return new ResponseEntity<>("Invoice deleted successfully!", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
}
