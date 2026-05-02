package com.zync.android.repository

import com.zync.android.api.ProjectService
import com.zync.android.database.ProjectDao
import com.zync.android.database.entities.ProjectEntity
import kotlinx.coroutines.flow.Flow

class ProjectRepository(private val projectDao: ProjectDao, private val projectService: ProjectService) {
    val allProjects: Flow<List<ProjectEntity>> = projectDao.getAllProjects()
    
    suspend fun refreshProjects() {
        try {
            val response = projectService.getProjects()
            if (response.isSuccessful && response.body() != null) {
                val entities = response.body()!!.map {
                    ProjectEntity(it.id, it.name, it.description, it.ownerId)
                }
                projectDao.insertAll(entities)
            }
        } catch (e: Exception) {
            // Fallback to cache silently
        }
    }
}
