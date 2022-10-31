package com.example.zenturaserver.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "labreports")
public class LabDTO {
    @Id
    private String id;

    private String patientName;
    private Date date;
    private String description;
    private String link;
    private Date createdAt;
    private Date updatedAt;
}
