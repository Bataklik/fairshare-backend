package com.fairshare.service;

import com.fairshare.model.User;
import com.fairshare.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepo;

    public UserService(UserRepository userRepo) {
        this.userRepo = userRepo;
    }

    public List<User> getAllUsers(){
        return userRepo.findAll();
    }

    public User createUser(User user) {
        return userRepo.save(user);
    }

    public User getUser(Long id) throws Exception {
        return userRepo.findById(id)
                .orElseThrow(() -> new Exception("User not found!"));
    }
}
