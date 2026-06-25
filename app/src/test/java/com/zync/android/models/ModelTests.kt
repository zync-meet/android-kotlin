package com.zync.android.models

import org.junit.Assert.assertEquals
import org.junit.Assert.assertNull
import org.junit.Test

class ModelTests {

    @Test
    fun testUserInstantiation() {
        val user = User(
            uid = "user_123",
            email = "test@zync.com",
            displayName = "Test User",
            photoURL = "https://photo.url/1.png",
            status = "online"
        )
        assertEquals("user_123", user.uid)
        assertEquals("test@zync.com", user.email)
        assertEquals("Test User", user.displayName)
        assertEquals("https://photo.url/1.png", user.photoURL)
        assertEquals("online", user.status)
    }

    @Test
    fun testProjectAndTaskInstantiation() {
        val project = Project(
            id = "proj_999",
            name = "Mobile Development",
            description = "Zync Android client task board",
            ownerId = "owner_1",
            team = listOf("user_123", "user_456")
        )
        val task = Task(
            id = "task_42",
            title = "Write Unit Tests",
            description = "Ensure 100% test coverage of all packages",
            status = "In Progress",
            assignedTo = "user_123",
            stepId = "step_1"
        )

        assertEquals("proj_999", project.id)
        assertEquals("Mobile Development", project.name)
        assertEquals(2, project.team.size)
        assertEquals("task_42", task.id)
        assertEquals("Write Unit Tests", task.title)
        assertEquals("In Progress", task.status)
        assertEquals("user_123", task.assignedTo)
    }

    @Test
    fun testMeetingInstantiation() {
        val meeting = Meeting(
            id = "meet_7",
            title = "Weekly Standup",
            hostId = "owner_1",
            meetLink = "https://meet.google.com/abc-defg-hij",
            status = "scheduled",
            startTime = "2026-06-25T10:00:00Z"
        )
        assertEquals("meet_7", meeting.id)
        assertEquals("Weekly Standup", meeting.title)
        assertEquals("https://meet.google.com/abc-defg-hij", meeting.meetLink)
        assertEquals("scheduled", meeting.status)
    }
}
