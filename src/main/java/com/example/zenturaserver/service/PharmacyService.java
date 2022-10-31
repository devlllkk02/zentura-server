package com.example.zenturaserver.service;

import com.example.zenturaserver.model.PharmacyDTO;

import java.util.List;

public interface PharmacyService {


    // -----    GET      -----
    public List<PharmacyDTO> getAllInvoices();

    public PharmacyDTO getAnInvoice(String id) throws Exception;

    // -----    POST      -----
    public void createInvoice(PharmacyDTO invoice) throws Exception;

    // -----    UPDATE      -----
    public void updateInvoice(String id, PharmacyDTO invoice) throws Exception;

    // -----    DELETE      -----
    public void deleteInvoice(String id) throws Exception;
}
