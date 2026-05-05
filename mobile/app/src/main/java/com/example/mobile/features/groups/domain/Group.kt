package com.example.mobile.features.groups.domain

data class Group(
    val id: Long,
    val name: String,
    val users: List<String>
)