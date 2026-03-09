package com.zync.android.models

data class Project(
    val id: String,
    val name: String,
    val description: String,
    val ownerId: String,
    val team: List<String>
)
