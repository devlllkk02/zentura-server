package com.example.zenturaserver.controller;

import com.example.zenturaserver.model.AppointmentDTO;
import com.example.zenturaserver.model.JobDTO;
import com.example.zenturaserver.service.AppointmentService;
import com.example.zenturaserver.service.JobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class AppointmentController {

    @Autowired
    private AppointmentService appointmentService;

    // -----    GET      -----
    @GetMapping("/appointment/getall")
    public ResponseEntity<?> getAllAppointments() {
        List<AppointmentDTO> appointments = appointmentService.getAllAppointments();
        return new ResponseEntity<>(appointments, HttpStatus.OK);
    }

    @GetMapping("/appointment/get/{id}")
    public ResponseEntity<?> getAnAppointment(@PathVariable("id") String id) {
        try {
            return new ResponseEntity<>(appointmentService.getAnAppointment(id), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    // -----    POST      -----
    @PostMapping("/appointment/create")
    public ResponseEntity<?> createAppointment(@RequestBody AppointmentDTO appointment) {
        try {
            appointmentService.createAppointment(appointment);
            return new ResponseEntity<AppointmentDTO>(appointment, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // -----    UPDATE      -----
    @PutMapping("/appointment/update/{id}")
    public ResponseEntity<?> updateAppointment(@PathVariable("id") String id, @RequestBody AppointmentDTO appointment) {

        try {
            appointmentService.updateAppointment(id, appointment);
            return new ResponseEntity<>("Appointment updated successfully!", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }

    }

    // -----    DELETE      -----
    @DeleteMapping("/appointment/delete/{id}")
    public ResponseEntity<?> deleteAppointment(@PathVariable("id") String id) {
        try {
            appointmentService.deleteAppointment(id);
            return new ResponseEntity<>("Appointment deleted successfully!", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
}
