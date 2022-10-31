package com.example.zenturaserver.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "patients")
public class PatientDTO {

    @Id
    private String id;

    private String firstName;
    private String lastName;
    private Date dateOfBirth;
    private String address;
    private String mobile;
    private Date createdAt;
    private Date updatedAt;
}
