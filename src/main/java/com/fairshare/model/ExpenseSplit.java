package com.fairshare.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;


@Entity
@Table(name="expense_split")
@Data
public class ExpenseSplit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private double amount;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "expense_id")
    private Expense expense;
}
