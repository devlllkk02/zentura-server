package com.example.zenturaserver.service;

import com.example.zenturaserver.model.JobDTO;

import java.util.List;

public interface JobService {
    // -----    GET      -----
    public List<JobDTO> getAllJobs();

    public JobDTO getAJob(String id) throws Exception;

    // -----    POST      -----
    public void createJob(JobDTO job) throws Exception;

    // -----    UPDATE      -----
    public void updateJob(String id, JobDTO job) throws Exception;

    // -----    DELETE      -----
    public void deleteJob(String id) throws Exception;
}
