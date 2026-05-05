package com.example.mobile.features.expenses.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mobile.core.network.RetrofitClient
import kotlinx.coroutines.launch

class ExpensesViewModel: ViewModel() {
    private val _data = MutableLiveData("No Data")
    val data : LiveData<String> get() = _data

    init{
        viewModelScope.launch {
            getExpense(2)
        }
    }


    private suspend fun getExpenses(){
     _data.value = RetrofitClient
         .expenseApiService
         .getExpenses()
         .toString()
    }

    private suspend fun getExpense(id:Int){
        _data.value = RetrofitClient
            .expenseApiService
            .getExpense(id)
            .toString()
    }
}