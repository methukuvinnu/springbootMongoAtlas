package com.example.vinnu.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection  = "tasks")
@Data
public class Task {

    @Id
    private String taskId;
    private int severity;

    private int storyPoint;

    private String assignee;

    private String description;


}
