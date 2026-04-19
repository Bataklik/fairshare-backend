package com.fairshare.service;

import com.fairshare.model.Group;
import com.fairshare.model.User;
import com.fairshare.repository.GroupRepository;
import com.fairshare.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GroupService {

    private final GroupRepository groupRepository;
    private final UserRepository userRepository;


    public GroupService(GroupRepository groupRepository, UserRepository userRepository) {
        this.groupRepository = groupRepository;
        this.userRepository = userRepository;
    }

    public List<Group> getAllGroups() {
        return groupRepository.findAll();
    }

    public Optional<Group> getGroup(long id) {
        return groupRepository.findById(id);
    }

    public Group createGroup(Group group) {
        return groupRepository.save(group);
    }

    public boolean addUserToGroup(Long toBeAddedUserId, Long groupId) throws Exception {
        User exstingUser = userRepository
                .findById(toBeAddedUserId)
                .orElseThrow(() -> new Exception("User doesn't exist!"));

        Group existingGroup = groupRepository
                .findById(groupId)
                .orElseThrow(() -> new Exception("Group doesn't exist!"));

        boolean isAdded = existingGroup.addUser(exstingUser);
        groupRepository.save(existingGroup);
        return isAdded;
    }

    public boolean removeUserFromGroup(Long userId, Long groupId) throws Exception {
        User exstingUser = userRepository
                .findById(userId)
                .orElseThrow(() -> new Exception("User doesn't exist!"));

        Group existingGroup = groupRepository
                .findById(groupId)
                .orElseThrow(() -> new Exception("Group doesn't exist!"));


        boolean isUserRemoved = existingGroup.removeUser(exstingUser);
        groupRepository.save(existingGroup);
        return isUserRemoved;
    }
}
