package com.fairshare.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name="groups")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Group {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @OneToMany
    @JoinTable(name = "group_members",
    joinColumns = @JoinColumn(name = "group_id"),
    inverseJoinColumns = @JoinColumn(name = "user_id"))
    private List<User> users;

    public Group(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public boolean addUser(User user) {
        if(user == null) return false;
        users.add(user);
        return true;
    }

    public boolean removeUser(User user) {
        if(user == null) return false;
        users.remove(user);
        return true;
    }
}
