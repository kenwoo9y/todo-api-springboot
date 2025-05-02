package com.example.todo.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
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
