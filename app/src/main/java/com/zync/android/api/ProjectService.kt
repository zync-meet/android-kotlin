package com.zync.android.api

import com.zync.android.models.Project
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface ProjectService {
    @GET("api/projects")
    suspend fun getProjects(): Response<List<Project>>
    
    @GET("api/projects/{id}")
    suspend fun getProjectById(@Path("id") id: String): Response<Project>
}
