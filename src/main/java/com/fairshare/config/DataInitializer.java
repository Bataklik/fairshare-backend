package com.fairshare.config;


import com.fairshare.model.User;
import com.fairshare.repository.ExpenseRepository;
import com.fairshare.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
@Log4j2
@RequiredArgsConstructor
public class DataInitializer {

    private final UserRepository repository;
    private final ExpenseRepository expenseRepository;

    @EventListener(ApplicationReadyEvent.class)
    public void runAfterStartup() {
        log.info("Systeem gestart, data inladen...");

        User newUser = new User();
        newUser.setName("John Doe");
        repository.save(newUser);

        log.info("Aantal users: {}", repository.count());
    }
}
