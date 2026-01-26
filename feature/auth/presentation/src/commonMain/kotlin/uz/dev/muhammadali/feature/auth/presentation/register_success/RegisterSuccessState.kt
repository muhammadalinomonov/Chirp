package uz.dev.muhammadali.feature.auth.presentation.register_success

import uz.dev.muhammadali.core.presentation.util.UiText

data class RegisterSuccessState(
    val registerEmail: String = "",
    val isResendingVerificationEmail: Boolean = false,
    val resendVerificationError: UiText? = null
)