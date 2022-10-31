package com.example.zenturaserver.service;

import com.example.zenturaserver.model.DoctorDTO;
import com.example.zenturaserver.model.PatientDTO;
import com.example.zenturaserver.repository.DoctorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class DoctorServiceImpl implements DoctorService {

    @Autowired
    private DoctorRepository doctorRepo;

    // -----    GET      -----
    @Override
    public List<DoctorDTO> getAllDoctors() {
        List<DoctorDTO> doctors = doctorRepo.findAll();
        if (doctors.size() > 0) {
            return doctors;
        } else {
            return new ArrayList<DoctorDTO>();
        }
    }

    @Override
    public DoctorDTO getADoctor(String id) throws Exception {
        Optional<DoctorDTO> optionalDoctor = doctorRepo.findById(id);
        if (!optionalDoctor.isPresent()) {
            throw new Exception("Doctor not found!");
        } else {
            return optionalDoctor.get();
        }
    }

    // -----    POST      -----
    @Override
    public void createDoctor(DoctorDTO doctor) throws Exception {
        try {
            if (doctor.getFirstName().equals("") || doctor.getLastName().equals("") ||  doctor.getDesignation().equals("") || doctor.getChannellingTime().equals("")) {
                throw new Exception("Please enter all the fields!");
            }
            doctor.setCreatedAt(new Date(System.currentTimeMillis()));
            doctor.setUpdatedAt(new Date(System.currentTimeMillis()));
            doctorRepo.save(doctor);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    // -----    UPDATE      -----
    @Override
    public void updateDoctor(String id, DoctorDTO doctor) throws Exception {

        Optional<DoctorDTO> doctorOptional = doctorRepo.findById(id);

        if (doctor.getFirstName().equals("") || doctor.getLastName().equals("") ||  doctor.getDesignation().equals("") || doctor.getChannellingTime().equals("")) {
            throw new Exception("Please enter all the fields!");
        }

        if (!doctorOptional.isPresent()) {
            throw new Exception("Doctor not found!");
        } else {
            DoctorDTO doctorToSave = doctorOptional.get();

            //First Name
            doctorToSave.setFirstName(doctor.getFirstName());
            //Last Name
            doctorToSave.setLastName(doctor.getLastName());
            //Designation
            doctorToSave.setDesignation(doctor.getDesignation());
            //Address
            doctorToSave.setChannellingTime(doctor.getChannellingTime());
            //Updated At
            doctorToSave.setUpdatedAt(new Date(System.currentTimeMillis()));

            doctorRepo.save(doctorToSave);
        }
    }

    // -----    DELETE      -----
    @Override
    public void deleteDoctor(String id) throws Exception {
        Optional<DoctorDTO> doctorOptional = doctorRepo.findById(id);

        if(!doctorOptional.isPresent()){
            throw new Exception("Doctor not found!");
        }else {
            doctorRepo.deleteById(id);
        }
    }
}
