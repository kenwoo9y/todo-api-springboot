package com.example.todo.repository;

import com.example.todo.model.User;
import java.util.List;
import java.util.Optional;
import org.apache.ibatis.annotations.Mapper;

/**
 * Repository interface for User entity.
 * Handles database operations for users.
 */
@Mapper
public interface UserRepository {
  /**
   * Retrieves all users from the database.
   *
   * @return List of all users
   */
  List<User> findAll();

  /**
   * Retrieves a user by their ID.
   *
   * @param id User ID
   * @return Optional containing the user if found
   */
  Optional<User> findById(Long id);

  /**
   * Retrieves a user by their username.
   *
   * @param username Username
   * @return Optional containing the user if found
   */
  Optional<User> findByUsername(String username);

  /**
   * Creates a new user in the database.
   *
   * @param user User to create
   */
  void create(User user);

  /**
   * Updates an existing user in the database.
   *
   * @param user User to update
   */
  void update(User user);

  /**
   * Deletes a user from the database.
   *
   * @param id ID of the user to delete
   */
  void delete(Long id);
}
