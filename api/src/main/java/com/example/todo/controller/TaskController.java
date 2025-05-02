package com.example.todo.controller;

import com.example.todo.model.Task;
import com.example.todo.service.TaskService;
import com.example.todo.service.UserService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

/**
 * REST controller for task management. Provides operations for creating, retrieving, updating, and
 * deleting tasks.
 */
@RestController
@RequiredArgsConstructor
public class TaskController {
  private final TaskService taskService;
  private final UserService userService;

  /**
   * Retrieves all tasks.
   *
   * @return Response containing a list of tasks
   */
  @GetMapping("/tasks")
  public ResponseEntity<List<Task>> getAllTasks() {
    return ResponseEntity.ok(taskService.findAll());
  }

  /**
   * Retrieves a task by its ID.
   *
   * @param id Task ID
   * @return Response containing the task information
   * @throws ResponseStatusException if the task is not found
   */
  @GetMapping("/tasks/{id}")
  public ResponseEntity<Task> getTaskById(@PathVariable Long id) {
    return taskService
        .findById(id)
        .map(ResponseEntity::ok)
        .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Task not found"));
  }

  /**
   * Retrieves all tasks for a specific user.
   *
   * @param ownerId ID of the user who owns the tasks
   * @return Response containing a list of tasks
   * @throws ResponseStatusException if the user is not found
   */
  @GetMapping("/users/{ownerId}/tasks")
  public ResponseEntity<List<Task>> getTasksByOwnerId(@PathVariable Long ownerId) {
    if (!userService.exists(ownerId)) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found");
    }

    return ResponseEntity.ok(taskService.findByOwnerId(ownerId));
  }

  /**
   * Creates a new task.
   *
   * @param task Task information to create
   * @return Response containing the created task information
   * @throws ResponseStatusException if the task information is invalid
   */
  @PostMapping("/tasks")
  public ResponseEntity<Task> createTask(@RequestBody Task task) {
    try {
      Task createdTask = taskService.create(task);
      return ResponseEntity.status(HttpStatus.CREATED).body(createdTask);
    } catch (IllegalArgumentException e) {
      throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY, e.getMessage());
    }
  }

  /**
   * Updates a task's information by its ID.
   *
   * @param id ID of the task to update
   * @param task Updated task information
   * @return Response containing the updated task information
   * @throws ResponseStatusException if the task is not found or the update information is invalid
   */
  @PatchMapping("/tasks/{id}")
  public ResponseEntity<Task> updateTask(@PathVariable Long id, @RequestBody Task task) {
    return taskService
        .findById(id)
        .map(
            existingTask -> {
              try {
                // Merge process for partial update
                if (task.getTitle() != null) {
                  existingTask.setTitle(task.getTitle());
                }
                if (task.getDescription() != null) {
                  existingTask.setDescription(task.getDescription());
                }
                if (task.getDueDate() != null) {
                  existingTask.setDueDate(task.getDueDate());
                }
                if (task.getStatus() != null) {
                  existingTask.setStatus(task.getStatus());
                }
                if (task.getOwnerId() != null) {
                  existingTask.setOwnerId(task.getOwnerId());
                }

                return ResponseEntity.ok(taskService.update(id, existingTask));
              } catch (IllegalArgumentException e) {
                throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY, e.getMessage());
              }
            })
        .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Task not found"));
  }

  /**
   * Deletes a task by its ID.
   *
   * @param id ID of the task to delete
   * @return Empty response on successful deletion
   * @throws ResponseStatusException if the task is not found
   */
  @DeleteMapping("/tasks/{id}")
  public ResponseEntity<Void> deleteTask(@PathVariable Long id) {
    if (!taskService.exists(id)) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Task not found");
    }
    taskService.delete(id);
    return ResponseEntity.noContent().build();
  }
}
