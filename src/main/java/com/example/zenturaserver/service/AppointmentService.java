package com.example.zenturaserver.service;

import com.example.zenturaserver.model.AppointmentDTO;

import java.util.List;

public interface AppointmentService {
    // -----    GET      -----
    public List<AppointmentDTO> getAllAppointments();

    public AppointmentDTO getAnAppointment(String id) throws Exception;

    // -----    POST      -----
    public void createAppointment(AppointmentDTO appointment) throws Exception;

    // -----    UPDATE      -----
    public void updateAppointment(String id, AppointmentDTO appointment) throws Exception;

    // -----    DELETE      -----
    public void deleteAppointment(String id) throws Exception;
}
