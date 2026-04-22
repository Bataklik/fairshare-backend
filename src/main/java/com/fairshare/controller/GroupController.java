package com.fairshare.controller;

import com.fairshare.model.Group;
import com.fairshare.service.GroupService;
import com.fairshare.service.BalanceService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/groups")
public class GroupController {

    private final GroupService groupService;
    private final BalanceService balanceService;

    public GroupController(GroupService groupService,BalanceService balanceService) {
        this.groupService = groupService;
        this.balanceService = balanceService;
    }

    @Operation(summary = "Get all groups", description = "Retrieves a list of all available groups")
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Group> getAllGroups() {
        return groupService.getAllGroups();
    }

    @Operation(summary = "Get group by id", description = "Returns a group by id")
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Optional<Group> getGroup(@PathVariable Long id) {
        return groupService.getGroup(id);
    }


    @Operation(summary = "Create a new group")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Group created successfully")
    })
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Group createGroup(@RequestBody Group group) {
        return groupService.createGroup(group);
    }


    @Operation(summary = "Add a user to a group", description = "Add a user to a group")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "User added successfully"),
            @ApiResponse(responseCode = "400", description = "Could not add user (user already in group)")
    })
    @PostMapping("/{groupId}/users/{userId}")
    public ResponseEntity<Void> addUserToGroup(@PathVariable Long groupId, @PathVariable Long userId) throws Exception {
        boolean success = groupService.addUserToGroup(userId, groupId);

        return success ? ResponseEntity.noContent().build() :
                ResponseEntity.badRequest().build();
    }

    @Operation(summary = "Delete a group", description = "Removes user from the group and returns a boolean")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved"),
    })
    @DeleteMapping("/{groupId}/users/{userId}")
    public ResponseEntity<Void> addRemoveFromGroup(@PathVariable Long groupId, @PathVariable Long userId) throws Exception {
        boolean success = groupService.removeUserFromGroup(userId, groupId);

        return success ? ResponseEntity.noContent().build() :
                ResponseEntity.badRequest().build();
    }


    @Operation(summary = "Get a group balance", description = "Returns balance of the group")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved"),
    })
    // TODO: ? -> BalanceDto
    @GetMapping("/{groupId}/balance")
    public ResponseEntity<?> getBalance(@PathVariable int groupId) throws Exception {
        List<Map<String, Object>> balance = balanceService
                .getBalance(groupId);

        return ResponseEntity.accepted().build();
    }

}
