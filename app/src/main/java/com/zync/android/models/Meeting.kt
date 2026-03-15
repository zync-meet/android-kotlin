package com.zync.android.models

data class Meeting(
    val id: String,
    val title: String,
    val hostId: String,
    val meetLink: String,
    val status: String,
    val startTime: String
)
