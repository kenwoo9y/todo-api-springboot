package com.example.todo.controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import com.example.todo.model.Task;
import com.example.todo.model.TaskStatus;
import com.example.todo.service.TaskService;
import com.example.todo.service.UserService;

@WebMvcTest(TaskController.class)
class TaskControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TaskService taskService;

    @MockBean
    private UserService userService;

    @Test
    void getAllTasks_ShouldReturnTasks() throws Exception {
        // テストデータの準備
        LocalDateTime now = LocalDateTime.now();
        List<Task> tasks = Arrays.asList(
            Task.builder()
                .id(1L)
                .title("買い物")
                .description("牛乳と卵を買う")
                .status(TaskStatus.TODO)
                .ownerId(1L)
                .createdAt(now)
                .updatedAt(now)
                .build(),
            Task.builder()
                .id(2L)
                .title("勉強")
                .description("Spring Bootの勉強")
                .status(TaskStatus.DOING)
                .ownerId(1L)
                .createdAt(now)
                .updatedAt(now)
                .build()
        );

        // モックの設定
        when(taskService.findAll()).thenReturn(tasks);

        // テストの実行と検証
        mockMvc.perform(get("/tasks"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$[0].id").value(1))
            .andExpect(jsonPath("$[0].title").value("買い物"))
            .andExpect(jsonPath("$[0].description").value("牛乳と卵を買う"))
            .andExpect(jsonPath("$[0].status").value("ToDo"))
            .andExpect(jsonPath("$[0].owner_id").value(1))
            .andExpect(jsonPath("$[1].id").value(2))
            .andExpect(jsonPath("$[1].title").value("勉強"))
            .andExpect(jsonPath("$[1].description").value("Spring Bootの勉強"))
            .andExpect(jsonPath("$[1].status").value("Doing"))
            .andExpect(jsonPath("$[1].owner_id").value(1));
    }

    @Test
    void getAllTasks_WhenNoTasks_ShouldReturnEmptyList() throws Exception {
        // モックの設定
        when(taskService.findAll()).thenReturn(List.of());

        // テストの実行と検証
        mockMvc.perform(get("/tasks"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());
    }
} 