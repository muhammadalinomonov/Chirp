package uz.dev.muhammadali.domain.domain.auth

import uz.dev.muhammadali.domain.domain.util.DataError
import uz.dev.muhammadali.domain.domain.util.EmptyResult
import uz.dev.muhammadali.domain.domain.util.Result

interface AuthService {

    suspend fun login(
        email: String,
        password: String
    ): Result<AuthInfo, DataError.Remote>

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