package com.example.zenturaserver.controller;

import com.example.zenturaserver.model.JobDTO;
import com.example.zenturaserver.model.PharmacyDTO;
import com.example.zenturaserver.service.JobService;
import com.example.zenturaserver.service.PharmacyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class JobController {

    @Autowired
    private JobService jobService;

    // -----    GET      -----
    @GetMapping("/job/getall")
    public ResponseEntity<?> getAllJobs() {
        List<JobDTO> jobs = jobService.getAllJobs();
        return new ResponseEntity<>(jobs,HttpStatus.OK);
    }

    @GetMapping("/job/get/{id}")
    public ResponseEntity<?> getAJob(@PathVariable("id") String id) {
        try {
            return new ResponseEntity<>(jobService.getAJob(id), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    // -----    POST      -----
    @PostMapping("/job/create")
    public ResponseEntity<?> createJob(@RequestBody JobDTO job) {
        try {
            jobService.createJob(job);
            return new ResponseEntity<JobDTO>(job, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // -----    UPDATE      -----
    @PutMapping("/job/update/{id}")
    public ResponseEntity<?> updateJob(@PathVariable("id") String id, @RequestBody JobDTO job) {

        try {
            jobService.updateJob(id, job);
            return new ResponseEntity<>("Job updated successfully!", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }

    }

    // -----    DELETE      -----
    @DeleteMapping("/job/delete/{id}")
    public ResponseEntity<?> deleteJob(@PathVariable("id") String id) {
        try {
            jobService.deleteJob(id);
            return new ResponseEntity<>("Job deleted successfully!", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
}
