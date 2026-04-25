package com.fairshare.mapper;

import com.fairshare.dto.CreateGroupDTO;
import com.fairshare.dto.GroupDTO;
import com.fairshare.model.Group;
import com.fairshare.model.User;

public class GroupMapper {

    public static Group toEntity(CreateGroupDTO createGroupDTO){
        return new Group(createGroupDTO.name());
    }

    public static GroupDTO toDto(Group group){
        return new GroupDTO(
                group.getId(),
                group.getName(),
                group.getUsers()
                        .stream()
                        .map(User::getName)
                        .toList()
        );
    }
}
