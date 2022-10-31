package com.example.zenturaserver.service;

import com.example.zenturaserver.model.PatientDTO;
import com.example.zenturaserver.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class PatientServiceImpl implements PatientService {

    @Autowired
    private PatientRepository patientRepo;

    // -----    GET      -----
    @Override
    public List<PatientDTO> getAllPatients() {
        List<PatientDTO> patients = patientRepo.findAll();
        if (patients.size() > 0) {
            return patients;
        } else {
            return new ArrayList<PatientDTO>();
        }
    }

    @Override
    public PatientDTO getAPatient(String id) throws Exception {
        Optional<PatientDTO> optionalPatient = patientRepo.findById(id);
        if (!optionalPatient.isPresent()) {
            throw new Exception("Patient not found!");
        } else {
            return optionalPatient.get();
        }
    }

    // -----    POST      -----
    @Override
    public void createPatient(PatientDTO patient) throws Exception {
        try {
            if (patient.getFirstName().equals("") || patient.getLastName().equals("") || patient.getDateOfBirth() == null || patient.getAddress().equals("") || patient.getMobile().equals("")) {
                throw new Exception("Please enter all the fields!");
            }
            patient.setCreatedAt(new Date(System.currentTimeMillis()));
            patient.setUpdatedAt(new Date(System.currentTimeMillis()));
            patientRepo.save(patient);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    // -----    UPDATE      -----
    @Override
    public void updatePatient(String id, PatientDTO patient) throws Exception {

        Optional<PatientDTO> patientOptional = patientRepo.findById(id);

        if (!patientOptional.isPresent()) {
            throw new Exception("Patient not found!");
        } else {
            PatientDTO patientToSave = patientOptional.get();

            //First Name
            patientToSave.setFirstName(patient.getFirstName());
            //Last Name
            patientToSave.setLastName(patient.getLastName());
            //Date of Birth
            patientToSave.setDateOfBirth(patient.getDateOfBirth());
            //Address
            patientToSave.setAddress(patient.getAddress());
            //Mobile
            patientToSave.setMobile(patient.getMobile());
            //Updated At
            patientToSave.setUpdatedAt(new Date(System.currentTimeMillis()));

            patientRepo.save(patientToSave);
        }
    }

    // -----    DELETE      -----

    @Override
    public void deletePatient(String id) throws Exception {
        Optional<PatientDTO> patientOptional = patientRepo.findById(id);

        if(!patientOptional.isPresent()){
            throw new Exception("Patient not found!");
        }else {
            patientRepo.deleteById(id);
        }
    }
}
