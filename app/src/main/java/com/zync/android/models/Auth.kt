package com.zync.android.models

data class LoginRequest(val email: String, val pass: String)
data class AuthResponse(val token: String)
