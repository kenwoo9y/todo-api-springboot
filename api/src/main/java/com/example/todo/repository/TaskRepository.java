package com.example.todo.repository;

import com.example.todo.model.Task;
import java.util.List;
import java.util.Optional;
import org.apache.ibatis.annotations.Mapper;

/**
 * Repository interface for Task entity.
 * Handles database operations for tasks.
 */
@Mapper
public interface TaskRepository {
  /**
   * Retrieves all tasks from the database.
   *
   * @return List of all tasks
   */
  List<Task> findAll();

  /**
   * Retrieves a task by its ID.
   *
   * @param id Task ID
   * @return Optional containing the task if found
   */
  Optional<Task> findById(Long id);

  /**
   * Retrieves all tasks owned by a specific user.
   *
   * @param ownerId ID of the user who owns the tasks
   * @return List of tasks owned by the user
   */
  List<Task> findByOwnerId(Long ownerId);

  /**
   * Creates a new task in the database.
   *
   * @param task Task to create
   */
  void create(Task task);

  /**
   * Updates an existing task in the database.
   *
   * @param task Task to update
   */
  void update(Task task);

  /**
   * Deletes a task from the database.
   *
   * @param id ID of the task to delete
   */
  void delete(Long id);
}
