package uz.dev.muhammadali.domain.domain.auth

import uz.dev.muhammadali.domain.domain.util.DataError
import uz.dev.muhammadali.domain.domain.util.EmptyResult

interface AuthService {
    suspend fun register(
        email: String,
        username: String,
        password: String
    ): EmptyResult<DataError.Remote>

    suspend fun resendVerificationEmail(
        email: String
    ): EmptyResult<DataError.Remote>

    suspend fun verifyEmail(token: String): EmptyResult<DataError.Remote>
}