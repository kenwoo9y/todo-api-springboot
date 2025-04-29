package com.example.todo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.todo.model.Task;
import com.example.todo.repository.TaskRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TaskService {
    private final TaskRepository taskRepository;

    public List<Task> findAll() {
        return taskRepository.findAll();
    }

    public Optional<Task> findById(Long id) {
        return taskRepository.findById(id);
    }

    public boolean exists(Long id) {
        return findById(id).isPresent();
    }

    @Transactional
    public Task create(Task task) {
        validateTask(task);
        taskRepository.create(task);
        return task;
    }

    @Transactional
    public Task update(Long id, Task task) {
        validateTask(task);
        task.setId(id);
        taskRepository.update(task);
        return task;
    }

    @Transactional
    public void delete(Long id) {
        taskRepository.delete(id);
    }

    private void validateTask(Task task) {
        if (task.getTitle() != null && task.getTitle().length() > 30) {
            throw new IllegalArgumentException("Title must be less than 30 characters");
        }
        if (task.getDescription() != null && task.getDescription().length() > 255) {
            throw new IllegalArgumentException("Description must be less than 255 characters");
        }
    }
} 