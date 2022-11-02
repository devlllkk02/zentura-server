package com.example.zenturaserver.service;

import com.example.zenturaserver.model.LabDTO;
import com.example.zenturaserver.repository.LabRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class LabServiceImpl implements LabService {

    @Autowired
    private LabRepository labRepo;

    // -----    GET      -----
    @Override
    public List<LabDTO> getAllLabReports() {
        List<LabDTO> labReports = labRepo.findAll();
        if (labReports.size() > 0) {
            return labReports;
        } else {
            return new ArrayList<LabDTO>();
        }
    }

    @Override
    public LabDTO getALabReport(String id) throws Exception {
        Optional<LabDTO> labReportOptional = labRepo.findById(id);
        if (!labReportOptional.isPresent()) {
            throw new Exception("Lab Report not found!");
        } else {
            return labReportOptional.get();
        }
    }
    
    // -----    POST      -----
    @Override
    public void createLabReport(LabDTO labReport) throws Exception {
        try {
            if (labReport.getPatientName().equals("")||  labReport.getDate() == null || labReport.getDescription().equals("") || labReport.getLink().equals("")) {
                throw new Exception("Please enter all the fields!");
            }
            labReport.setCreatedAt(new Date(System.currentTimeMillis()));
            labReport.setUpdatedAt(new Date(System.currentTimeMillis()));
            labRepo.save(labReport);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    // -----    UPDATE      -----
    @Override
    public void updateLabReport(String id, LabDTO labReport) throws Exception {

        Optional<LabDTO> labReportOptional = labRepo.findById(id);

        if (labReport.getPatientName().equals("")||  labReport.getDate() == null || labReport.getDescription().equals("") || labReport.getLink().equals("")) {
            throw new Exception("Please enter all the fields!");
        }

        if (!labReportOptional.isPresent()) {
            throw new Exception("Lab report not found!");
        } else {
            LabDTO labReportToSave = labReportOptional.get();

            //Patient Name
            labReportToSave.setPatientName(labReport.getPatientName());
            //Date
            labReportToSave.setDate(labReport.getDate());
            //Description
            labReportToSave.setDescription(labReport.getDescription());
            //Link
            labReportToSave.setLink(labReport.getLink());
            //Updated At
            labReportToSave.setUpdatedAt(new Date(System.currentTimeMillis()));

            labRepo.save(labReportToSave);
        }
    }

    // -----    DELETE      -----

    @Override
    public void deleteLabReport(String id) throws Exception {
        Optional<LabDTO> labReportOptional = labRepo.findById(id);

        if(!labReportOptional.isPresent()){
            throw new Exception("Lab report not found!");
        }else {
            labRepo.deleteById(id);
        }
    }
}
