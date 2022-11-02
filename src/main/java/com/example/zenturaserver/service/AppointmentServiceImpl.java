package com.example.zenturaserver.service;

import com.example.zenturaserver.model.AppointmentDTO;
import com.example.zenturaserver.repository.AppointmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class AppointmentServiceImpl implements AppointmentService {

    @Autowired
    private AppointmentRepository appointmentRepo;

    // -----    GET      -----
    @Override
    public List<AppointmentDTO> getAllAppointments() {
        List<AppointmentDTO> appointments = appointmentRepo.findAll();
        if (appointments.size() > 0) {
            return appointments;
        } else {
            return new ArrayList<AppointmentDTO>();
        }
    }

    @Override
    public AppointmentDTO getAnAppointment(String id) throws Exception {
        Optional<AppointmentDTO> appointmentOptional = appointmentRepo.findById(id);
        if (!appointmentOptional.isPresent()) {
            throw new Exception("Appointment not found!");
        } else {
            return appointmentOptional.get();
        }
    }

    // -----    POST      -----
    @Override
    public void createAppointment(AppointmentDTO appointment) throws Exception {
        try {

            if (appointment.getPatientName().equals("") || appointment.getDoctorName().equals("") || appointment.getDate() == null || appointment.getRemarks().equals("")) {
                throw new Exception("Please enter all the fields!");
            }
            appointment.setCreatedAt(new Date(System.currentTimeMillis()));
            appointment.setUpdatedAt(new Date(System.currentTimeMillis()));
            appointmentRepo.save(appointment);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    // -----    UPDATE      -----
    @Override
    public void updateAppointment(String id, AppointmentDTO appointment) throws Exception {

        Optional<AppointmentDTO> appointmentOptional = appointmentRepo.findById(id);

        if (appointment.getPatientName().equals("") || appointment.getDoctorName().equals("") || appointment.getDate() == null || appointment.getRemarks().equals("")) {
            throw new Exception("Please enter all the fields!");
        }

        if (!appointmentOptional.isPresent()) {
            throw new Exception("Appointment not found!");
        } else {
            AppointmentDTO appointmentToSave = appointmentOptional.get();

            //Patient Name
            appointmentToSave.setPatientName(appointment.getPatientName());
            //Doctor Name
            appointmentToSave.setDoctorName(appointment.getDoctorName());
            //Date
            appointmentToSave.setDate(appointment.getDate());
            //Remarks
            appointmentToSave.setRemarks(appointment.getRemarks());

            //Updated At
            appointmentToSave.setUpdatedAt(new Date(System.currentTimeMillis()));

            appointmentRepo.save(appointmentToSave);
        }
    }

    // -----    DELETE      -----
    @Override
    public void deleteAppointment(String id) throws Exception {
        Optional<AppointmentDTO> appointmentOptional = appointmentRepo.findById(id);

        if (!appointmentOptional.isPresent()) {
            throw new Exception("Appointment not found!");
        } else {
            appointmentRepo.deleteById(id);
        }
    }
}
