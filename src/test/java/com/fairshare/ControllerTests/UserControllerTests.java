package com.fairshare.ControllerTests;

import com.fairshare.controller.UserController;
import com.fairshare.model.User;
import com.fairshare.repository.ExpenseRepository;
import com.fairshare.repository.UserRepository;
import com.fairshare.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(UserController.class)
public class UserControllerTests {


    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private UserService userServiceMock;
    @MockitoBean
    private UserRepository userRepository;
    @MockitoBean
    private ExpenseRepository expenseRepository;

    private final ObjectMapper objectMapper = new ObjectMapper();


    @Test
    @WithMockUser
    public void getAllUsers_returns_AllUsers() throws Exception {
        //? Arrange
        User user_a = new User(1L, "Jan", List.of());
        User user_b = new User(2L, "Piet",List.of());
        when(userServiceMock.getAllUsers())
                .thenReturn(Arrays.asList(user_a, user_b));

        mockMvc.perform(get("/users")
                        .with(csrf()))
                .andExpect(status().isOk())
                .andExpect((jsonPath("$.length()").value(2)))
                .andExpect(jsonPath("$[0].name").value("Jan"));
    }

    @Test
    @WithMockUser
    public void getUser_returns_User() throws Exception {
        //? Arrange
        User user_b = new User(2L, "Piet",List.of());
        when(userServiceMock.getUser(2L))
                .thenReturn(Optional.of(user_b));

        mockMvc.perform(get("/users/" + 2L)
                        .with(csrf()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Piet"));
    }

    @Test
    @WithMockUser
    public void createUser_returns_CreatedUser() throws Exception {
        //? Arrange
        String userJson = "{\"id\":1, \"name\":\"Karel\",\"groups\":[]}";
        User createUser = new User(1L, "Karel",List.of());
        when(userServiceMock.createUser(createUser))
                .thenReturn(createUser);

        mockMvc.perform(post("/users")
                        .with(csrf())
                        .contentType("application/json")
                        .content(userJson))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.name").value("Karel"));
    }
}
