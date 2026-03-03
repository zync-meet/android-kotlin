package com.zync.android.models

data class User(
    val uid: String,
    val email: String,
    val displayName: String,
    val photoURL: String?,
    val status: String
)
