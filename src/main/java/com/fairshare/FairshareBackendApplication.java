package com.fairshare;

import com.fairshare.model.Expense;
import com.fairshare.model.User;
import com.fairshare.repository.ExpenseRepository;
import com.fairshare.repository.UserRepository;
import lombok.extern.log4j.Log4j2;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.boot.persistence.autoconfigure.EntityScan;
import org.springframework.context.event.EventListener;

import java.util.List;

@Log4j2
@EntityScan("com.fairshare.model")
@SpringBootApplication
public class FairshareBackendApplication {

	@Autowired
	private UserRepository repository;
	@Autowired
	private ExpenseRepository expenseRepository;

	private static final Logger logger = LogManager.getLogger(FairshareBackendApplication.class);

	@EventListener(ApplicationReadyEvent.class)
	public void runAfterStartup() {
		List<User> allUsers = this.repository.findAll();
		logger.info("Number of customers: {}", allUsers.size());

		User newUser = new User();
		newUser.setName("John Doe");
		logger.info("Saving new customer...");
		this.repository.save(newUser);

		allUsers = this.repository.findAll();
		logger.info("Number of customers: {}", allUsers.size());



		List<Expense> allExpenses = this.expenseRepository.findAll();
		logger.info("Number of customers: {}", allUsers.size());

		Expense newExpense = new Expense();
		newExpense.setName("Baco");
		logger.info("Saving new expense...");
		this.expenseRepository.save(newExpense);

		allExpenses = this.expenseRepository.findAll();
		logger.info("Number of expenses: {}", allExpenses.size());
	}


	public static void main(String[] args) {
		SpringApplication.run(FairshareBackendApplication.class, args);
	}

}
