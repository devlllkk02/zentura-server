package com.example.zenturaserver.service;

import com.example.zenturaserver.model.PharmacyDTO;

import java.util.List;

public interface PharmacyService {
    // -----    GET      -----
    public List<PharmacyDTO> getAllPharmacy();

    public PharmacyDTO getAPharmacy(String id) throws Exception;

    // -----    POST      -----
    public void createPharmacy(PharmacyDTO patient) throws Exception;

    // -----    UPDATE      -----
    public void updatePharmacy(String id, PharmacyDTO pharmacy) throws Exception;

    // -----    DELETE      -----
    public void deletePharmacy(String id) throws Exception;
}
