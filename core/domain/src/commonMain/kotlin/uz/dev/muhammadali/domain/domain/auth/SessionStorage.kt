package uz.dev.muhammadali.domain.domain.auth

import kotlinx.coroutines.flow.Flow

interface SessionStorage {
    fun observeAuthInfo(): Flow<AuthInfo?>
    suspend fun set(authInfo: AuthInfo?)
}