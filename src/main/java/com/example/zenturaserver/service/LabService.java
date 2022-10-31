package com.example.zenturaserver.service;

import com.example.zenturaserver.model.DoctorDTO;
import com.example.zenturaserver.model.LabDTO;
import com.example.zenturaserver.model.PatientDTO;

import java.util.List;

public interface LabService {

    // -----    GET      -----
    public List<LabDTO> getAllLabReports();

    public LabDTO getALabReport(String id) throws Exception;

    // -----    POST      -----
    public void createLabReport(LabDTO labReport) throws Exception;

    // -----    UPDATE      -----
    public void updateLabReport(String id, LabDTO labReport) throws Exception;

    // -----    DELETE      -----
    public void deleteLabReport(String id) throws Exception;
}
