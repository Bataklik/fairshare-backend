package com.example.mobile.features.expenses.domain

import com.example.mobile.features.users.domain.User

data class Expense(
    val id: Long,
    val name: String,
    val amount: Double,
    val category:String,
    val paidBy: User,
    val splits: List<ExpenseSplit>
)

data class ExpenseSplit(val user: User,
                        val amount: Double)

