package com.example.zenturaserver.repository;

import com.example.zenturaserver.model.LabDTO;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface LabRepository extends MongoRepository<LabDTO,String> {
}
