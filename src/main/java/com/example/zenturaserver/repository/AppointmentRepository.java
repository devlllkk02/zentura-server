package com.example.zenturaserver.repository;

import com.example.zenturaserver.model.AppointmentDTO;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AppointmentRepository extends MongoRepository<AppointmentDTO,String> {
}
