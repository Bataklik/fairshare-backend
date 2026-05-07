package com.example.mobile.features.groups.domain

data class Group(
    val id: Long,
    val name: String,
    val icon:String?,
    val users: List<String>
)