package com.example.todo.repository;

import java.util.List;
import java.util.Optional;

import org.apache.ibatis.annotations.Mapper;

import com.example.todo.model.Task;

@Mapper
public interface TaskRepository {
    List<Task> findAll();
    Optional<Task> findById(Long id);
    List<Task> findByOwnerId(Long ownerId);
    void create(Task task);
    void update(Task task);
    void delete(Long id);
} 