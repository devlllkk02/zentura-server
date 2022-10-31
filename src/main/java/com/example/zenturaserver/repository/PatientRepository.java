package com.example.zenturaserver.repository;

import com.example.zenturaserver.model.PatientModel;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PatientRepository extends MongoRepository<PatientModel,String> {
}
