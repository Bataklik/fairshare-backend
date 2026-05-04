package com.example.mobile.models

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

