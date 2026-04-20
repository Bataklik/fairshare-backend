package com.fairshare.service;

import com.fairshare.model.Expense;
import com.fairshare.model.User;
import com.fairshare.repository.ExpenseRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ExpenseService {

    private final ExpenseRepository expenseRepository;

    public ExpenseService(ExpenseRepository expenseRepository) {
        this.expenseRepository = expenseRepository;
    }

    public List<Expense> getAllExpenses() {
        return expenseRepository.findAll();
    }

    public Expense createExpense(Expense newExpense){
        return expenseRepository.save(newExpense);
    }

    public Optional<Expense> getExpenseById(Long expenseId){
        return expenseRepository.findById(expenseId);
    }

    public List<Expense> getExpenseByPaidBy(User paidByUser){

        return expenseRepository.findByPaidBy(paidByUser);
    }
}
