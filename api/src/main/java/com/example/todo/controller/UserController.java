package com.example.todo.controller;

import com.example.todo.model.User;
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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

/**
 * REST controller for user management.
 * Provides operations for creating, retrieving, updating, and deleting users.
 */
@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {
  private final UserService userService;

  /**
   * Retrieves all users.
   *
   * @return Response containing a list of users
   */
  @GetMapping
  public ResponseEntity<List<User>> getAllUsers() {
    return ResponseEntity.ok(userService.findAll());
  }

  /**
   * Retrieves a user by their ID.
   *
   * @param id User ID
   * @return Response containing the user information
   * @throws ResponseStatusException if the user is not found
   */
  @GetMapping("/{id}")
  public ResponseEntity<User> getUserById(@PathVariable Long id) {
    return userService
        .findById(id)
        .map(ResponseEntity::ok)
        .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));
  }

  /**
   * Retrieves a user by their username.
   *
   * @param username Username
   * @return Response containing the user information
   * @throws ResponseStatusException if the user is not found
   */
  @GetMapping("/username/{username}")
  public ResponseEntity<User> getUserByUsername(@PathVariable String username) {
    return userService
        .findByUsername(username)
        .map(ResponseEntity::ok)
        .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));
  }

  /**
   * Creates a new user.
   *
   * @param user User information to create
   * @return Response containing the created user information
   * @throws ResponseStatusException if the user information is invalid
   */
  @PostMapping
  public ResponseEntity<User> createUser(@RequestBody User user) {
    try {
      User createdUser = userService.create(user);
      return ResponseEntity.status(HttpStatus.CREATED).body(createdUser);
    } catch (IllegalArgumentException e) {
      throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY, e.getMessage());
    }
  }

  /**
   * Updates a user's information by their ID.
   *
   * @param id ID of the user to update
   * @param user Updated user information
   * @return Response containing the updated user information
   * @throws ResponseStatusException if the user is not found or the update information is invalid
   */
  @PatchMapping("/{id}")
  public ResponseEntity<User> updateUser(@PathVariable Long id, @RequestBody User user) {
    return userService
        .findById(id)
        .map(
            existingUser -> {
              try {
                // Merge process for partial update
                if (user.getUsername() != null) existingUser.setUsername(user.getUsername());
                if (user.getEmail() != null) existingUser.setEmail(user.getEmail());
                if (user.getFirstName() != null) existingUser.setFirstName(user.getFirstName());
                if (user.getLastName() != null) existingUser.setLastName(user.getLastName());

                return ResponseEntity.ok(userService.update(id, existingUser));
              } catch (IllegalArgumentException e) {
                throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY, e.getMessage());
              }
            })
        .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));
  }

  /**
   * Deletes a user by their ID.
   *
   * @param id ID of the user to delete
   * @return Empty response on successful deletion
   * @throws ResponseStatusException if the user is not found
   */
  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
    if (!userService.exists(id)) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found");
    }
    userService.delete(id);
    return ResponseEntity.noContent().build();
  }
}
