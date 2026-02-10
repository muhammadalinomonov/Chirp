package uz.dev.muhammadali.core.data.dto

import kotlinx.serialization.Serializable
import uz.dev.muhammadali.domain.domain.auth.User

@Serializable
data class UserSerializable(
    val id: String,
    val email: String,
    val username: String,
    val hasVerifiedEmail: Boolean,
    val profilePictureUrl: String? = null
)