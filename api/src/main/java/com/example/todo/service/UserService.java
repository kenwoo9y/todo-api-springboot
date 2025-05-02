package com.example.todo.service;

import com.example.todo.model.User;
import com.example.todo.repository.UserRepository;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/** Service class for user management. Handles business logic for user operations. */
@Service
@RequiredArgsConstructor
public class UserService {
  private final UserRepository userRepository;

  /**
   * Retrieves all users.
   *
   * @return List of all users
   */
  public List<User> findAll() {
    return userRepository.findAll();
  }

  /**
   * Retrieves a user by their ID.
   *
   * @param id User ID
   * @return Optional containing the user if found
   */
  public Optional<User> findById(Long id) {
    return userRepository.findById(id);
  }

  /**
   * Retrieves a user by their username.
   *
   * @param username Username
   * @return Optional containing the user if found
   */
  public Optional<User> findByUsername(String username) {
    return userRepository.findByUsername(username);
  }

  /**
   * Checks if a user exists by their ID.
   *
   * @param id User ID
   * @return true if the user exists, false otherwise
   */
  public boolean exists(Long id) {
    return findById(id).isPresent();
  }

  /**
   * Creates a new user.
   *
   * @param user User to create
   * @return Created user
   * @throws IllegalArgumentException if the user information is invalid
   */
  @Transactional
  public User create(User user) {
    validateUser(user);
    userRepository.create(user);
    return user;
  }

  /**
   * Updates an existing user.
   *
   * @param id ID of the user to update
   * @param user Updated user information
   * @return Updated user
   * @throws IllegalArgumentException if the user information is invalid
   */
  @Transactional
  public User update(Long id, User user) {
    validateUser(user);
    user.setId(id);
    userRepository.update(user);
    return user;
  }

  /**
   * Deletes a user by their ID.
   *
   * @param id ID of the user to delete
   */
  @Transactional
  public void delete(Long id) {
    userRepository.delete(id);
  }

  /**
   * Validates user information.
   *
   * @param user User to validate
   * @throws IllegalArgumentException if any validation fails
   */
  private void validateUser(User user) {
    if (user.getUsername() != null && user.getUsername().length() > 30) {
      throw new IllegalArgumentException("Username must be less than 30 characters");
    }
    if (user.getEmail() != null && user.getEmail().length() > 80) {
      throw new IllegalArgumentException("Email must be less than 80 characters");
    }
    if (user.getFirstName() != null && user.getFirstName().length() > 40) {
      throw new IllegalArgumentException("First name must be less than 40 characters");
    }
    if (user.getLastName() != null && user.getLastName().length() > 40) {
      throw new IllegalArgumentException("Last name must be less than 40 characters");
    }
  }
}
