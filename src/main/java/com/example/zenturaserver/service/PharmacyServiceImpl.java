package com.example.zenturaserver.service;

import com.example.zenturaserver.model.PharmacyDTO;
import com.example.zenturaserver.repository.PharmacyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class PharmacyServiceImpl implements PharmacyService {

    @Autowired
    private PharmacyRepository pharmacyRepo;

    // -----    GET      -----
    @Override
    public List<PharmacyDTO> getAllInvoices() {
        List<PharmacyDTO> invoices = pharmacyRepo.findAll();
        if (invoices.size() > 0) {
            return invoices;
        } else {
            return new ArrayList<PharmacyDTO>();
        }
    }

    @Override
    public PharmacyDTO getAnInvoice(String id) throws Exception {
        Optional<PharmacyDTO> invoiceOptional = pharmacyRepo.findById(id);
        if (!invoiceOptional.isPresent()) {
            throw new Exception("Invoice not found!");
        } else {
            return invoiceOptional.get();
        }
    }

    // -----    POST      -----
    @Override
    public void createInvoice(PharmacyDTO invoice) throws Exception {
        try {

            if (invoice.getPatientName().equals("") || invoice.getDoctorName().equals("") || invoice.getDate() == null || invoice.getMedicine().equals("")) {
                throw new Exception("Please enter all the fields!");
            }
            invoice.setCreatedAt(new Date(System.currentTimeMillis()));
            invoice.setUpdatedAt(new Date(System.currentTimeMillis()));
            pharmacyRepo.save(invoice);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    // -----    UPDATE      -----
    @Override
    public void updateInvoice(String id, PharmacyDTO invoice) throws Exception {

        Optional<PharmacyDTO> pharmacyOptional = pharmacyRepo.findById(id);

        if (invoice.getPatientName().equals("") || invoice.getDoctorName().equals("") || invoice.getDate() == null || invoice.getMedicine().equals("")) {
            throw new Exception("Please enter all the fields!");
        }

        if (!pharmacyOptional.isPresent()) {
            throw new Exception("Invoice not found!");
        } else {
            PharmacyDTO invoiceToSave = pharmacyOptional.get();

            //Patient Name
            invoiceToSave.setPatientName(invoice.getPatientName());
            //Doctor Name
            invoiceToSave.setDoctorName(invoice.getDoctorName());
            //Date
            invoiceToSave.setDate(invoice.getDate());
            //Medicine
            invoiceToSave.setMedicine(invoice.getMedicine());

            //Updated At
            invoiceToSave.setUpdatedAt(new Date(System.currentTimeMillis()));

            pharmacyRepo.save(invoiceToSave);
        }
    }

    // -----    DELETE      -----

    @Override
    public void deleteInvoice(String id) throws Exception {
        Optional<PharmacyDTO> pharmacyOptional = pharmacyRepo.findById(id);

        if (!pharmacyOptional.isPresent()) {
            throw new Exception("Invoice not found!");
        } else {
            pharmacyRepo.deleteById(id);
        }
    }
}
