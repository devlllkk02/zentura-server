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
@Document(collection = "jobs")
public class JobDTO {

    @Id
    private String id;

    private String patientName;
    private String doctorName;
    private Date date;
    private String description;
    private Date createdAt;
    private Date updatedAt;
}
