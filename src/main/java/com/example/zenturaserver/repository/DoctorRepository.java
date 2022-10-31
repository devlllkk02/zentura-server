package com.example.zenturaserver.repository;

import com.example.zenturaserver.model.DoctorDTO;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface DoctorRepository extends MongoRepository<DoctorDTO,String> {
}
