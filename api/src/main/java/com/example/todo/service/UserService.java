package com.example.todo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.todo.model.User;
import com.example.todo.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public Optional<User> findById(Long id) {
        return userRepository.findById(id);
    }

    public Optional<User> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public boolean exists(Long id) {
        return findById(id).isPresent();
    }

    @Transactional
    public User create(User user) {
        validateUser(user);
        userRepository.create(user);
        return user;
    }

    @Transactional
    public User update(Long id, User user) {
        validateUser(user);
        user.setId(id);
        userRepository.update(user);
        return user;
    }

    @Transactional
    public void delete(Long id) {
        userRepository.delete(id);
    }

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