package com.fairshare.ControllerTests;

import com.fairshare.controller.UserController;
import com.fairshare.model.User;
import com.fairshare.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
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

    @Test
    @WithMockUser
    public void getAllUsers_returns_AllUsers() throws Exception {
        //? Arrange
        User user_a = new User(1L, "Jan", List.of());
        User user_b = new User(2L, "Piet",List.of());
        when(userServiceMock.getAllUsers())
                .thenReturn(Arrays.asList(user_a, user_b));

        //? Act + Assert
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
                .thenReturn(user_b);

        //? Act + Assert
        mockMvc.perform(get("/users/" + 2L)
                        .with(csrf()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Piet"));
    }

    @Test
    @WithMockUser
    public void createUser_returns_CreatedUser() throws Exception {

        //? Arrange
        String requestJson = "{\"name\":\"Karel\"}";
        User savedUser = new User(1L, "Karel", List.of());
        when(userServiceMock.createUser(any(User.class)))
                .thenReturn(savedUser);

        //? Act + Assert
        mockMvc.perform(post("/users")
                        .with(csrf())
                        .contentType("application/json")
                        .content(requestJson))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name").value("Karel"))
                .andExpect(jsonPath("$.id").value(1));
    }
}
