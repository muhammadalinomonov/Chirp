package uz.dev.muhammadali.core.data.auth

import io.ktor.client.HttpClient
import uz.dev.muhammadali.core.data.dto.requests.EmailRequest
import uz.dev.muhammadali.core.data.dto.requests.RegisterRequest
import uz.dev.muhammadali.core.data.networking.get
import uz.dev.muhammadali.core.data.networking.post
import uz.dev.muhammadali.domain.domain.auth.AuthService
import uz.dev.muhammadali.domain.domain.util.DataError
import uz.dev.muhammadali.domain.domain.util.EmptyResult

class KtorAuthService(
    private val httpClient: HttpClient
) : AuthService {
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