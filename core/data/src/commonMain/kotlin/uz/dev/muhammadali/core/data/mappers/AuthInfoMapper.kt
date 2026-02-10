package uz.dev.muhammadali.core.data.mappers

import uz.dev.muhammadali.core.data.dto.AuthInfoSerializable
import uz.dev.muhammadali.core.data.dto.UserSerializable
import uz.dev.muhammadali.domain.domain.auth.AuthInfo
import uz.dev.muhammadali.domain.domain.auth.User

fun AuthInfoSerializable.toDomain(): AuthInfo {
    return AuthInfo(
        accessToken = accessToken,
        refreshToken = refreshToken,
        user = user.toDomain()
    )
}

fun UserSerializable.toDomain(): User {
    return User(
        id = id,
        email = email,
        username = username,
        hasVerifiedEmail = hasVerifiedEmail,
    )
}