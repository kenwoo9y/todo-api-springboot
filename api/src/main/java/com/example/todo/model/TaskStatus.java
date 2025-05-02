package com.example.todo.model;

import com.fasterxml.jackson.annotation.JsonValue;

public enum TaskStatus {
  TODO("ToDo"),
  DOING("Doing"),
  DONE("Done");

  private final String value;

  TaskStatus(String value) {
    this.value = value;
  }

  @JsonValue
  public String getValue() {
    return value;
  }

  public static TaskStatus fromValue(String value) {
    for (TaskStatus status : TaskStatus.values()) {
      if (status.value.equalsIgnoreCase(value)) {
        return status;
      }
    }
    throw new IllegalArgumentException("Invalid TaskStatus: " + value);
  }
}
