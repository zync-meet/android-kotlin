package com.zync.android.fakes

import com.zync.android.api.ProjectService
import com.zync.android.database.ProjectDao
import com.zync.android.database.entities.ProjectEntity
import com.zync.android.models.Project
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import okhttp3.ResponseBody.Companion.toResponseBody
import retrofit2.Response

import com.zync.android.api.AuthService
import com.zync.android.models.LoginRequest
import com.zync.android.models.AuthResponse

class FakeProjectDao : ProjectDao {
    private val projectsFlow = MutableStateFlow<List<ProjectEntity>>(emptyList())

    override fun getAllProjects(): Flow<List<ProjectEntity>> = projectsFlow

    override suspend fun insertAll(projects: List<ProjectEntity>) {
        projectsFlow.value = projects
    }
}

class FakeProjectService : ProjectService {
    var shouldReturnError = false
    var projectsToReturn = listOf<Project>()

    override suspend fun getProjects(): Response<List<Project>> {
        return if (shouldReturnError) {
            Response.error(500, "Error fetching projects".toResponseBody())
        } else {
            Response.success(projectsToReturn)
        }
    }

    override suspend fun getProjectById(id: String): Response<Project> {
        val project = projectsToReturn.find { it.id == id }
        return if (project != null && !shouldReturnError) {
            Response.success(project)
        } else {
            Response.error(404, "Not found".toResponseBody())
        }
    }
}

class FakeAuthService : AuthService {
    var shouldReturnError = false
    var responseToken = "token_abc123"

    override suspend fun login(request: LoginRequest): Response<AuthResponse> {
        return if (shouldReturnError) {
            Response.error(401, "Invalid credentials".toResponseBody())
        } else {
            Response.success(AuthResponse(responseToken))
        }
    }
}
