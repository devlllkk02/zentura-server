package com.example.zenturaserver.repository;

import com.example.zenturaserver.model.PharmacyDTO;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PharmacyRepository extends MongoRepository<PharmacyDTO,String> {
}
