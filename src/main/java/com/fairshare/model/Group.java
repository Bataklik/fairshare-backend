package com.fairshare.model;


import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Table(name="groups")
@Data
public class Group {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @OneToMany
    @JoinColumn(name = "user_id")
    private List<User> users;
}
