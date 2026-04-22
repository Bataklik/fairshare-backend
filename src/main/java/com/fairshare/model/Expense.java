package com.fairshare.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "expenses")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Expense {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private double amount;

    @Enumerated(EnumType.STRING)
    private ExpenseCategory category;

    @ManyToOne
    @JoinColumn(name = "paid_by_id")
    private User paidBy;

    @OneToMany(mappedBy = "expense",
            cascade = CascadeType.ALL,
            orphanRemoval= true)
    private List<ExpenseSplit> splits = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "group_id")
    private Group group;

    public Expense(String name, double amount, ExpenseCategory category, User paidBy,Group group) {
        this.name = name;
        this.amount = amount;
        this.category = category;
        this.paidBy = paidBy;
        this.group = group;
    }

    public void addSplit(ExpenseSplit expenseSplit){
        splits.add(expenseSplit);
    }

    public boolean removeSplitByExpenseSplit(ExpenseSplit expenseSplit){
        return splits.remove(expenseSplit);
    }
    public boolean removeSplitById(Long expenseSplitId){
        return getSplits()
                .removeIf(ep -> Objects.equals(ep.getId(), expenseSplitId));
    }
}
