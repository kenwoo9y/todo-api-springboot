package com.example.todo.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Data;

/**
 * Task entity class.
 * Represents a task in the system with its details and status.
 */
@Data
@Builder
public class Task {
  /**
   * Unique identifier for the task.
   */
  private Long id;

  /**
   * Title of the task.
   * Must be less than 30 characters.
   */
  private String title;

  /**
   * Detailed description of the task.
   * Must be less than 255 characters.
   */
  private String description;

  /**
   * Due date and time for the task.
   */
  @JsonProperty("due_date")
  private LocalDateTime dueDate;

  /**
   * Current status of the task.
   * Can be TODO, IN_PROGRESS, or DONE.
   */
  private TaskStatus status;

  /**
   * ID of the user who owns this task.
   */
  @JsonProperty("owner_id")
  private Long ownerId;

  @JsonProperty("created_at")
  private LocalDateTime createdAt;

  @JsonProperty("updated_at")
  private LocalDateTime updatedAt;
}
