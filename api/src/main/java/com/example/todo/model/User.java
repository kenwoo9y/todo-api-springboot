package com.example.todo.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Data;

/** User entity class. Represents a user in the system with their personal information. */
@Data
@Builder
public class User {
  /** Unique identifier for the user. */
  private Long id;

  /** Username for login and identification. Must be unique and less than 30 characters. */
  private String username;

  /** User's email address. Must be less than 80 characters. */
  private String email;

  /** User's first name. Must be less than 40 characters. */
  @JsonProperty("first_name")
  private String firstName;

  /** User's last name. Must be less than 40 characters. */
  @JsonProperty("last_name")
  private String lastName;

  @JsonProperty("created_at")
  private LocalDateTime createdAt;

  @JsonProperty("updated_at")
  private LocalDateTime updatedAt;
}
