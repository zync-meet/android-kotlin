package com.zync.android.repository

import com.zync.android.api.AuthService
import com.zync.android.api.RetrofitClient
import com.zync.android.models.LoginRequest

class AuthRepository(
    private val authService: AuthService = RetrofitClient.instance.create(AuthService::class.java)
) {
    
    suspend fun login(email: String, pass: String): Boolean {
        return try {
            val response = authService.login(LoginRequest(email, pass))
            response.isSuccessful
        } catch (e: Exception) {
            false
        }
    }
}
