package com.fairshare.ControllerTests;

import com.fairshare.controller.GroupController;
import com.fairshare.model.Group;
import com.fairshare.service.BalanceService;
import com.fairshare.service.GroupService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(GroupController.class)
public class GroupControllerTests {
    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private GroupService groupServiceMock;
    @MockitoBean
    private BalanceService balanceService;

    @Test
    @WithMockUser
    public void getAllGroups_returns_AllGroups() throws Exception {
        //? Arrange
        Group group_a = new Group(1L,"De spenders", List.of());
        Group group_b = new Group(2L, "Gulzigen",List.of());
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
        Group group_b = new Group(2L, "Gulzigen",List.of());
        when(groupServiceMock.getGroup(2L))
                .thenReturn(group_b);

        mockMvc.perform(get("/groups/" + 2L)
                        .with(csrf()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Gulzigen"));
    }

    @Test
    @WithMockUser
    public void createUser_returns_CreatedUser() throws Exception {
        //? Arrange
        String groupJson = "{\"name\":\"Gulzigen\", \"users\":[]}";
        Group createGroup = new Group(1L, "Gulzigen",List.of());
        when(groupServiceMock.createGroup(any(Group.class)))
                .thenReturn(createGroup);

        mockMvc.perform(post("/groups")
                        .with(csrf())
                        .contentType("application/json")
                        .content(groupJson))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name").value("Gulzigen"))
                .andExpect(jsonPath("$.users").isEmpty());
    }

    @Test
    @WithMockUser
    public void addUserToGroup_returns_NoContent() throws Exception {
        //? Arrange
        Long userId = 3L;
        Long groupId = 1L;

        when(groupServiceMock.addUserToGroup(userId,groupId))
                .thenReturn(true);

        mockMvc.perform(post("/groups/"+groupId+"/users/" + userId)
                        .with(csrf()))
                .andExpect(status().isNoContent());

    }

    @Test
    @WithMockUser
    public void addUserToGroup_returns_BadRequest() throws Exception {
        //? Arrange
        Long userId = 3L;
        Long groupId = 1L;

        when(groupServiceMock.addUserToGroup(userId,groupId))
                .thenReturn(false);

        mockMvc.perform(post("/groups/"+groupId+"/users/" + userId)
                        .with(csrf()))
                .andExpect(status().isBadRequest());

    }

    @Test
    @WithMockUser
    public void removeUserFromGroup_returns_noContent() throws Exception {
        //? Arrange
        Long userId = 3L;
        Long groupId = 1L;

        when(groupServiceMock.removeUserFromGroup(userId,groupId))
                .thenReturn(true);

        mockMvc.perform(delete("/groups/"+groupId+"/users/" + userId)
                        .with(csrf()))
                .andExpect(status().isNoContent());

    }
}
