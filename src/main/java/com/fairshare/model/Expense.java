package com.fairshare.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "expenses")
@Data
@Getter
@Setter
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
            cascade = CascadeType.ALL)
    private List<ExpenseSplit> splits;

    @ManyToOne
    @JoinColumn(name = "group_id")
    private Group group;

}
