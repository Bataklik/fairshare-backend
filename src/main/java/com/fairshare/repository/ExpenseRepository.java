package com.fairshare.repository;

import com.fairshare.model.Expense;
import com.fairshare.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ExpenseRepository extends JpaRepository<Expense,Long> {

    List<Expense> findByPaidBy(User paidBy);
}
