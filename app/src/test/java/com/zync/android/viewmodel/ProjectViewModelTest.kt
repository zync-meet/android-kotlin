package com.zync.android.viewmodel

import com.zync.android.database.entities.ProjectEntity
import com.zync.android.fakes.FakeProjectDao
import com.zync.android.fakes.FakeProjectService
import com.zync.android.models.Project
import com.zync.android.repository.ProjectRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.*
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class ProjectViewModelTest {

    private val testDispatcher = StandardTestDispatcher()
    private lateinit var fakeDao: FakeProjectDao
    private lateinit var fakeService: FakeProjectService
    private lateinit var repository: ProjectRepository
    private lateinit var viewModel: ProjectViewModel

    @Before
    fun setUp() {
        Dispatchers.setMain(testDispatcher)
        fakeDao = FakeProjectDao()
        fakeService = FakeProjectService()
        repository = ProjectRepository(fakeDao, fakeService)
        viewModel = ProjectViewModel(repository)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun testViewModelExposesCachedProjects() = runTest {
        val cached = listOf(
            ProjectEntity("p1", "Offline Project", "Cached Desc", "owner_1")
        )
        fakeDao.insertAll(cached)

        // StandardTestDispatcher requires trigger/scheduler execution
        testScheduler.advanceUntilIdle()

        val observed = viewModel.projects.first()
        assertEquals(1, observed.size)
        assertEquals("Offline Project", observed[0].name)
    }

    @Test
    fun testFetchProjectsTriggersNetworkRefresh() = runTest {
        val remote = listOf(
            Project("p2", "Online Project", "Remote Desc", "owner_2", emptyList())
        )
        fakeService.projectsToReturn = remote

        viewModel.fetchProjects()
        testScheduler.advanceUntilIdle()

        val observed = viewModel.projects.first()
        assertEquals(1, observed.size)
        assertEquals("Online Project", observed[0].name)
    }
}
