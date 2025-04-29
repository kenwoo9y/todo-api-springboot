package com.example.todo.model;

public enum TaskStatus {
    TODO("ToDo"),
    DOING("Doing"),
    DONE("Done");

    private final String value;

    TaskStatus(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static TaskStatus fromValue(String value) {
        for (TaskStatus status : TaskStatus.values()) {
            if (status.value.equals(value)) {
                return status;
            }
        }
        throw new IllegalArgumentException("Invalid TaskStatus: " + value);
    }
} 