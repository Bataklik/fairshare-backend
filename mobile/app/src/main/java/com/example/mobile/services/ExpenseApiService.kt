package com.example.mobile.services

import com.example.mobile.models.Expense
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

// https://www.youtube.com/watch?v=F5sj_PFzzx0&t=239s
interface ExpenseApiService {
    @GET("expenses")
    suspend fun getExpenses(): List<Expense>

    @GET("expenses/{id}")
    suspend fun getExpense(
        @Path("id") id:Int
    ):Expense
}