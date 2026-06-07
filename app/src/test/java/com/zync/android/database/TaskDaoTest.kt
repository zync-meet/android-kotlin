package com.zync.android.database

import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import com.zync.android.database.entities.TaskEntity
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.Assert.assertEquals

class TaskDaoTest {
    private lateinit var db: AppDatabase
    private lateinit var taskDao: TaskDao
    
    @Before
    fun setup() {
        db = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            AppDatabase::class.java
        ).allowMainThreadQueries().build()
        taskDao = db.taskDao()
    }
    
    @After
    fun teardown() = db.close()
    
    @Test
    fun testInsertAndGetTasks() = runBlocking {
        val task = TaskEntity("t1", "Test Task", "Desc", "Backlog", null, "step1")
        taskDao.insertAll(listOf(task))
        val result = taskDao.getTasksForStep("step1").first()
        assertEquals(1, result.size)
        assertEquals("Test Task", result[0].title)
    }
}
