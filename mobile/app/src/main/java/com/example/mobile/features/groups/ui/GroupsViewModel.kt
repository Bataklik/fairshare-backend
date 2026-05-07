package com.example.mobile.features.groups.ui

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mobile.core.network.RetrofitClient
import com.example.mobile.features.groups.domain.Group
import kotlinx.coroutines.launch

class GroupsViewModel : ViewModel() {

    var groupState by mutableStateOf<List<Group>>(emptyList())
        private set
    var selectedGroup by mutableStateOf<Group?>(null)
        private set

    init {
        viewModelScope.launch {
            getGroups()
            print(groupState)
        }
    }

    private suspend fun getGroups() {
        try {
            val groups = RetrofitClient
                .groupApiService
                .getGroups()

            groupState = groups
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    private suspend fun getGroup(id: Int) {
        try {
            val group = RetrofitClient
                .groupApiService
                .getGroup(id)

            selectedGroup = group
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}