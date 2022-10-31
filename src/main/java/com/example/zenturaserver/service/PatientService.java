package com.example.zenturaserver.service;

import com.example.zenturaserver.model.PatientDTO;

import javax.validation.ConstraintViolationException;
import java.util.List;

public interface PatientService {
    // -----    GET      -----
    public List<PatientDTO> getAllPatients();

    public PatientDTO getAPatient(String id) throws Exception;

    // -----    POST      -----
    public void createPatient(PatientDTO patient) throws Exception;

    // -----    UPDATE      -----
    public void updatePatient(String id, PatientDTO patient) throws Exception;

    // -----    DELETE      -----
    public void deletePatient(String id) throws Exception;
}
