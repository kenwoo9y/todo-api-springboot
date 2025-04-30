package com.example.todo.repository;

import java.util.List;
import java.util.Optional;

import org.apache.ibatis.annotations.Mapper;

import com.example.todo.model.User;

@Mapper
public interface UserRepository {
    List<User> findAll();
    Optional<User> findById(Long id);
    Optional<User> findByUsername(String username);
    void create(User user);
    void update(User user);
    void delete(Long id);
} 