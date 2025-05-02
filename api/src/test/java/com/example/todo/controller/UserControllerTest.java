package com.example.todo.controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.example.todo.model.User;
import com.example.todo.service.UserService;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(UserController.class)
class UserControllerTest {

  @Autowired private MockMvc mockMvc;

  @MockBean private UserService userService;

  @Test
  void getAllUsers_ShouldReturnUsers() throws Exception {
    // テストデータの準備
    LocalDateTime now = LocalDateTime.now();
    List<User> users =
        Arrays.asList(
            User.builder()
                .id(1L)
                .username("user1")
                .email("user1@example.com")
                .firstName("John")
                .lastName("Doe")
                .createdAt(now)
                .updatedAt(now)
                .build(),
            User.builder()
                .id(2L)
                .username("user2")
                .email("user2@example.com")
                .firstName("Jane")
                .lastName("Smith")
                .createdAt(now)
                .updatedAt(now)
                .build());

    // モックの設定
    when(userService.findAll()).thenReturn(users);

    // テストの実行と検証
    mockMvc
        .perform(get("/users"))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$[0].id").value(1))
        .andExpect(jsonPath("$[0].username").value("user1"))
        .andExpect(jsonPath("$[0].email").value("user1@example.com"))
        .andExpect(jsonPath("$[0].first_name").value("John"))
        .andExpect(jsonPath("$[0].last_name").value("Doe"))
        .andExpect(jsonPath("$[1].id").value(2))
        .andExpect(jsonPath("$[1].username").value("user2"))
        .andExpect(jsonPath("$[1].email").value("user2@example.com"))
        .andExpect(jsonPath("$[1].first_name").value("Jane"))
        .andExpect(jsonPath("$[1].last_name").value("Smith"));
  }

  @Test
  void getAllUsers_WhenNoUsers_ShouldReturnEmptyList() throws Exception {
    // モックの設定
    when(userService.findAll()).thenReturn(List.of());

    // テストの実行と検証
    mockMvc
        .perform(get("/users"))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$").isArray())
        .andExpect(jsonPath("$").isEmpty());
  }
}
