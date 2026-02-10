package uz.dev.muhammadali.core.data.auth

import io.ktor.client.HttpClient
import uz.dev.muhammadali.core.data.dto.AuthInfoSerializable
import uz.dev.muhammadali.core.data.dto.requests.EmailRequest
import uz.dev.muhammadali.core.data.dto.requests.LoginRequest
import uz.dev.muhammadali.core.data.dto.requests.RegisterRequest
import uz.dev.muhammadali.core.data.mappers.toDomain
import uz.dev.muhammadali.core.data.networking.get
import uz.dev.muhammadali.core.data.networking.post
import uz.dev.muhammadali.domain.domain.auth.AuthInfo
import uz.dev.muhammadali.domain.domain.auth.AuthService
import uz.dev.muhammadali.domain.domain.util.DataError
import uz.dev.muhammadali.domain.domain.util.EmptyResult
import uz.dev.muhammadali.domain.domain.util.Result
import uz.dev.muhammadali.domain.domain.util.map

class KtorAuthService(
    private val httpClient: HttpClient
) : AuthService {
    override suspend fun login(
        email: String,
        password: String
    ): Result<AuthInfo, DataError.Remote> {
        return httpClient.post<LoginRequest, AuthInfoSerializable>(
            route = "/auth/login",
            body = LoginRequest(
                email = email,
                password = password
            )
        ).map { authInfoSerializable ->
            authInfoSerializable.toDomain()
        }
    }

    override suspend fun register(
        email: String,
        username: String,
        password: String
    ): EmptyResult<DataError.Remote> {
        return httpClient.post(
            route = "/auth/register",
            body = RegisterRequest(
                email = email,
                username = username,
                password = password
            )
        )
    }

    override suspend fun resendVerificationEmail(email: String): EmptyResult<DataError.Remote> {
        return httpClient.post(
            route = "/auth/resend-verification",
            body = EmailRequest(
                email = email
            )
        )
    }

    override suspend fun verifyEmail(token: String): EmptyResult<DataError.Remote> {
        return httpClient.get(
            route = "/auth/verify",
            queryParameters = mapOf("token" to token)
        )
    }
}