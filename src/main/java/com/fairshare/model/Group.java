package com.fairshare.model;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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

    @ManyToMany(mappedBy = "groups")
    @JsonIgnoreProperties("groups")
    private List<User> users = new java.util.ArrayList<>();


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
