package com.zync.android.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.zync.android.database.entities.ProjectEntity
import com.zync.android.database.entities.TaskEntity

@Database(entities = [ProjectEntity::class, TaskEntity::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun projectDao(): ProjectDao
    abstract fun taskDao(): TaskDao
}
