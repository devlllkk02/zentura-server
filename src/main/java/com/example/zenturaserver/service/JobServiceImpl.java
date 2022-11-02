package com.example.zenturaserver.service;

import com.example.zenturaserver.model.JobDTO;
import com.example.zenturaserver.model.PharmacyDTO;
import com.example.zenturaserver.repository.JobsRepository;
import com.example.zenturaserver.repository.PharmacyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class JobServiceImpl implements JobService {

    @Autowired
    private JobsRepository jobsRepo;

    // -----    GET      -----
    @Override
    public List<JobDTO> getAllJobs() {
        List<JobDTO> jobs = jobsRepo.findAll();
        if (jobs.size() > 0) {
            return jobs;
        } else {
            return new ArrayList<JobDTO>();
        }
    }

    @Override
    public JobDTO getAJob(String id) throws Exception {
        Optional<JobDTO> jobOptional = jobsRepo.findById(id);
        if (!jobOptional.isPresent()) {
            throw new Exception("Job not found!");
        } else {
            return jobOptional.get();
        }
    }

    // -----    POST      -----
    @Override
    public void createJob(JobDTO invoice) throws Exception {
        try {

            if (invoice.getPatientName().equals("") || invoice.getDoctorName().equals("") || invoice.getDate() == null || invoice.getDescription().equals("")) {
                throw new Exception("Please enter all the fields!");
            }
            invoice.setCreatedAt(new Date(System.currentTimeMillis()));
            invoice.setUpdatedAt(new Date(System.currentTimeMillis()));
            jobsRepo.save(invoice);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    // -----    UPDATE      -----
    @Override
    public void updateJob(String id, JobDTO job) throws Exception {

        Optional<JobDTO> jobOptional = jobsRepo.findById(id);

        if (job.getPatientName().equals("") || job.getDoctorName().equals("") || job.getDate() == null || job.getDescription().equals("")) {
            throw new Exception("Please enter all the fields!");
        }

        if (!jobOptional.isPresent()) {
            throw new Exception("Job not found!");
        } else {
            JobDTO jobToSave = jobOptional.get();

            //Patient Name
            jobToSave.setPatientName(job.getPatientName());
            //Doctor Name
            jobToSave.setDoctorName(job.getDoctorName());
            //Date
            jobToSave.setDate(job.getDate());
            //Description
            jobToSave.setDescription(job.getDescription());
            //Link

            //Updated At
            jobToSave.setUpdatedAt(new Date(System.currentTimeMillis()));

            jobsRepo.save(jobToSave);
        }
    }

    // -----    DELETE      -----

    @Override
    public void deleteJob(String id) throws Exception {
        Optional<JobDTO> jobOptional = jobsRepo.findById(id);

        if (!jobOptional.isPresent()) {
            throw new Exception("Job not found!");
        } else {
            jobsRepo.deleteById(id);
        }
    }
}
