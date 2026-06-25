package com.zync.android.ui

import com.zync.android.database.entities.ProjectEntity
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

class UiStateTests {

    // Simulates the state machine for the UI navigation and flow
    @Test
    fun testNavigationFlowState() {
        var currentScreen = "welcome"
        val navigateTo: (String) -> Unit = { screen -> currentScreen = screen }

        assertEquals("welcome", currentScreen)

        // Simulate click Login
        navigateTo("login")
        assertEquals("login", currentScreen)

        // Simulate login success
        navigateTo("dashboard")
        assertEquals("dashboard", currentScreen)
    }

    @Test
    fun testDashboardUiStateFiltering() {
        val projects = listOf(
            ProjectEntity("p1", "Task Planner", "Manage cards", "owner_1"),
            ProjectEntity("p2", "Meeting Hub", "Schedule meets", "owner_2")
        )

        // Simulate UI search query filter
        val searchQuery = "Planner"
        val filtered = projects.filter { it.name.contains(searchQuery, ignoreCase = true) }

        assertEquals(1, filtered.size)
        assertEquals("Task Planner", filtered[0].name)
    }
}
