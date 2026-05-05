package com.example.mobile.features.groups.data

import com.example.mobile.features.groups.domain.Group
import retrofit2.http.GET
import retrofit2.http.Path

interface GroupApiService {
    @GET("/groups")
    suspend fun getGroups():List<Group>

    @GET("/groups/{id}")
    suspend fun getGroup(@Path("id") id: Int): Group
}