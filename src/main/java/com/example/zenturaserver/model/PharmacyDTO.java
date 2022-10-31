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
@Document(collection = "pharmacy")
public class PharmacyDTO {

    @Id
    private String id;

    private String patientName;
    private String doctorName;
    private Date date;
    private String medicine;
    private Date createdAt;
    private Date updatedAt;
}
