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
@Document(collection = "appointments")
public class AppointmentDTO {

    @Id
    private String id;

    private String patientName;
    private String doctorName;
    private Date date;
    private String remarks;
    private Date createdAt;
    private Date updatedAt;
}
