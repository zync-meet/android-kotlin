package com.zync.android.repository

import com.zync.android.fakes.FakeAuthService
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test

class AuthRepositoryTest {

    private lateinit var fakeService: FakeAuthService
    private lateinit var repository: AuthRepository

    @Before
    fun setUp() {
        fakeService = FakeAuthService()
        repository = AuthRepository(fakeService)
    }

    @Test
    fun testLoginSuccess() = runBlocking {
        fakeService.shouldReturnError = false
        val result = repository.login("user@zync.com", "pass123")
        assertTrue(result)
    }

    @Test
    fun testLoginFailure() = runBlocking {
        fakeService.shouldReturnError = true
        val result = repository.login("user@zync.com", "wrong_pass")
        assertFalse(result)
    }

    @Test
    fun testLoginException() = runBlocking {
        val throwyService = object : com.zync.android.api.AuthService {
            override suspend fun login(request: com.zync.android.models.LoginRequest): retrofit2.Response<com.zync.android.models.AuthResponse> {
                throw java.io.IOException("Network Timeout")
            }
        }
        val throwyRepo = AuthRepository(throwyService)
        val result = throwyRepo.login("user@zync.com", "pass123")
        assertFalse(result)
    }
}
