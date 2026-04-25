package com.fairshare.model;


import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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

    @ManyToMany(mappedBy = "groups", cascade=CascadeType.ALL)
    @JsonIgnoreProperties("groups")
    private List<User> users = new ArrayList<>();

    public Group(String name) {
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
