package com.example.todo.service;

import com.example.todo.model.Task;
import com.example.todo.repository.TaskRepository;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/** Service class for task management. Handles business logic for task operations. */
@Service
@RequiredArgsConstructor
public class TaskService {
  private final TaskRepository taskRepository;

  /**
   * Retrieves all tasks.
   *
   * @return List of all tasks
   */
  public List<Task> findAll() {
    return taskRepository.findAll();
  }

  /**
   * Retrieves a task by its ID.
   *
   * @param id Task ID
   * @return Optional containing the task if found
   */
  public Optional<Task> findById(Long id) {
    return taskRepository.findById(id);
  }

  /**
   * Retrieves all tasks for a specific user.
   *
   * @param ownerId ID of the user who owns the tasks
   * @return List of tasks owned by the user
   */
  public List<Task> findByOwnerId(Long ownerId) {
    return taskRepository.findByOwnerId(ownerId);
  }

  /**
   * Checks if a task exists by its ID.
   *
   * @param id Task ID
   * @return true if the task exists, false otherwise
   */
  public boolean exists(Long id) {
    return findById(id).isPresent();
  }

  /**
   * Creates a new task.
   *
   * @param task Task to create
   * @return Created task
   * @throws IllegalArgumentException if the task information is invalid
   */
  @Transactional
  public Task create(Task task) {
    validateTask(task);
    taskRepository.create(task);
    return task;
  }

  /**
   * Updates an existing task.
   *
   * @param id ID of the task to update
   * @param task Updated task information
   * @return Updated task
   * @throws IllegalArgumentException if the task information is invalid
   */
  @Transactional
  public Task update(Long id, Task task) {
    validateTask(task);
    task.setId(id);
    taskRepository.update(task);
    return task;
  }

  /**
   * Deletes a task by its ID.
   *
   * @param id ID of the task to delete
   */
  @Transactional
  public void delete(Long id) {
    taskRepository.delete(id);
  }

  /**
   * Validates task information.
   *
   * @param task Task to validate
   * @throws IllegalArgumentException if any validation fails
   */
  private void validateTask(Task task) {
    if (task.getTitle() != null && task.getTitle().length() > 30) {
      throw new IllegalArgumentException("Title must be less than 30 characters");
    }
    if (task.getDescription() != null && task.getDescription().length() > 255) {
      throw new IllegalArgumentException("Description must be less than 255 characters");
    }
  }
}
