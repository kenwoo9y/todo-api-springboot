package com.example.todo.model;

import com.fasterxml.jackson.annotation.JsonValue;

/** Enum representing the possible statuses of a task. */
public enum TaskStatus {
  /** Task has been created but not yet started. */
  TODO("ToDo"),

  /** Task is currently being worked on. */
  DOING("Doing"),

  /** Task has been completed. */
  DONE("Done");

  private final String value;

  TaskStatus(String value) {
    this.value = value;
  }

  /**
   * Returns the string representation of the task status.
   *
   * @return String representation of the task status
   */
  @JsonValue
  public String getValue() {
    return value;
  }

  /**
   * Converts a string value to the corresponding TaskStatus enum.
   *
   * @param value String representation of the task status
   * @return Corresponding TaskStatus enum
   * @throws IllegalArgumentException if the value does not match any TaskStatus
   */
  public static TaskStatus fromValue(String value) {
    for (TaskStatus status : TaskStatus.values()) {
      if (status.value.equals(value)) {
        return status;
      }
    }
    throw new IllegalArgumentException("Invalid TaskStatus value: " + value);
  }
}
