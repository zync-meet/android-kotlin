package com.zync.android.repository

import com.zync.android.database.entities.ProjectEntity
import com.zync.android.fakes.FakeProjectDao
import com.zync.android.fakes.FakeProjectService
import com.zync.android.models.Project
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test

class ProjectRepositoryTest {

    private lateinit var fakeDao: FakeProjectDao
    private lateinit var fakeService: FakeProjectService
    private lateinit var repository: ProjectRepository

    @Before
    fun setUp() {
        fakeDao = FakeProjectDao()
        fakeService = FakeProjectService()
        repository = ProjectRepository(fakeDao, fakeService)
    }

    @Test
    fun testInitialProjectsIsEmpty() = runBlocking {
        val projects = repository.allProjects.first()
        assertTrue(projects.isEmpty())
    }

    @Test
    fun testRefreshProjectsSuccess() = runBlocking {
        val remoteProjects = listOf(
            Project("p1", "Project 1", "Desc 1", "owner_1", listOf("u1")),
            Project("p2", "Project 2", "Desc 2", "owner_1", listOf("u1"))
        )
        fakeService.projectsToReturn = remoteProjects

        repository.refreshProjects()

        val cachedProjects = repository.allProjects.first()
        assertEquals(2, cachedProjects.size)
        assertEquals("Project 1", cachedProjects[0].name)
        assertEquals("Desc 2", cachedProjects[1].description)
    }

    @Test
    fun testRefreshProjectsNetworkFailureGracefullyHandled() = runBlocking {
        // Pre-populate cache
        val initialCache = listOf(
            ProjectEntity("p1", "Project Cached", "Cached description", "owner_1")
        )
        fakeDao.insertAll(initialCache)

        // Set service to fail
        fakeService.shouldReturnError = true

        repository.refreshProjects()

        // Cache should still contain the initial project and not be cleared or crash
        val cachedProjects = repository.allProjects.first()
        assertEquals(1, cachedProjects.size)
        assertEquals("Project Cached", cachedProjects[0].name)
    }
}
