package com.fairshare.controller;

import com.fairshare.model.Group;
import com.fairshare.service.GroupService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/groups")
public class GroupController {

    private final GroupService groupService;

    public GroupController(GroupService groupService) {
        this.groupService = groupService;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Group> getAllGroups() {
        return groupService.getAllGroups();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Optional<Group> getGroup(@PathVariable Long id) {
        return groupService.getGroup(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Group createGroup(@RequestBody Group group) {
        return groupService.createGroup(group);
    }


    @PostMapping("/{groupId}/users/{userId}")
    public ResponseEntity<Void> addUserToGroup(@PathVariable Long groupId, @PathVariable Long userId) throws Exception {
        boolean success = groupService.addUserToGroup(userId, groupId);

        return success ? ResponseEntity.noContent().build() :
                ResponseEntity.badRequest().build();
    }

    @DeleteMapping("/{groupId}/users/{userId}")
    public ResponseEntity<Void> addRemoveFromGroup(@PathVariable Long groupId, @PathVariable Long userId) throws Exception {
        boolean success = groupService.removeUserFromGroup(userId, groupId);

        return success ? ResponseEntity.noContent().build() :
                ResponseEntity.badRequest().build();
    }

}
