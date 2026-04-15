package com.fairshare.ControllerTests;

import com.fairshare.controller.GroupController;
import com.fairshare.model.Group;
import com.fairshare.repository.ExpenseRepository;
import com.fairshare.repository.GroupRepository;
import com.fairshare.service.GroupService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.Optional;

import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(GroupController.class)
public class GroupControllerTests {
    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private GroupService groupServiceMock;
    @MockitoBean
    private GroupRepository groupRepository;
    @MockitoBean
    private ExpenseRepository expenseRepository;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Test
    @WithMockUser
    public void getAllGroups_returns_AllGroups() throws Exception {
        //? Arrange
        Group group_a = new Group(1L,"De spenders");
        Group group_b = new Group(2L, "Gulzigen");
        when(groupServiceMock.getAllGroups())
                .thenReturn(Arrays.asList(group_a, group_b));

        mockMvc.perform(get("/groups")
                        .with(csrf()))
                .andExpect(status().isOk())
                .andExpect((jsonPath("$.length()").value(2)))
                .andExpect(jsonPath("$[0].name").value("De spenders"));
    }

    @Test
    @WithMockUser
    public void getGroup_returns_Group() throws Exception {
        //? Arrange
        Group group_b = new Group(2L, "Gulzigen");
        when(groupServiceMock.getGroup(2L))
                .thenReturn(Optional.of(group_b));

        mockMvc.perform(get("/groups/" + 2L)
                        .with(csrf()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Gulzigen"));
    }

    @Test
    @WithMockUser
    public void createUser_returns_CreatedUser() throws Exception {
        //? Arrange
        String groupJson = "{\"id\":1, \"name\":\"Gulzigen\"}";
        Group createGroup = new Group(1L, "Gulzigen");
        when(groupServiceMock.createGroup(createGroup))
                .thenReturn(createGroup);

        mockMvc.perform(post("/groups")
                        .with(csrf())
                        .contentType("application/json")
                        .content(groupJson))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.name").value("Gulzigen"));
    }
}
