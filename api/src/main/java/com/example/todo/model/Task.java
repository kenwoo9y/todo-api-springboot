package com.example.todo.model;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class Task {
    private Long id;
    private String title;
    private String description;
    
    @JsonProperty("due_date")
    private LocalDateTime dueDate;
    
    private TaskStatus status;
    
    @JsonProperty("owner_id")
    private Long ownerId;
    
    @JsonProperty("created_at")
    private LocalDateTime createdAt;
    
    @JsonProperty("updated_at")
    private LocalDateTime updatedAt;
} 