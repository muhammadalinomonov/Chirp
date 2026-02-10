package uz.dev.muhammadali.domain.domain.auth

data class AuthInfo(
    val accessToken: String,
    val refreshToken: String,
    val user: User
)
