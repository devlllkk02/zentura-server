package com.example.zenturaserver.repository;

import com.example.zenturaserver.model.JobDTO;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JobsRepository extends MongoRepository<JobDTO,String> {
}
