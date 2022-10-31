package com.example.zenturaserver.service;

import com.example.zenturaserver.model.DoctorDTO;

import java.util.List;

public interface DoctorService {


    // -----    GET      -----
    public List<DoctorDTO> getAllDoctors();

    public DoctorDTO getADoctor(String id) throws Exception;

    // -----    POST      -----
    public void createDoctor(DoctorDTO patient) throws Exception;

    // -----    UPDATE      -----
    public void updateDoctor(String id, DoctorDTO patient) throws Exception;

    // -----    DELETE      -----
    public void deleteDoctor(String id) throws Exception;
}
