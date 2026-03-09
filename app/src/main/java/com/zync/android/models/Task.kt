package com.zync.android.models

data class Task(
    val id: String,
    val title: String,
    val description: String?,
    val status: String,
    val assignedTo: String?,
    val stepId: String
)
