package com.example.mobile.features.groups.ui

import androidx.compose.ui.tooling.data.Group
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mobile.core.network.RetrofitClient
import kotlinx.coroutines.launch

class GroupsViewModel : ViewModel(){

    private val _data = MutableLiveData("No data")
    val data: MutableLiveData<String> = _data

    init {
        viewModelScope.launch {
            getGroup(1)
        }
    }

    private suspend fun getGroups(){
        _data.value = RetrofitClient
            .groupApiService
            .getGroups()
            .toString()
    }

    private suspend fun getGroup(id: Int){
        _data.value = RetrofitClient
            .groupApiService
            .getGroup(id)
            .toString()
    }
}