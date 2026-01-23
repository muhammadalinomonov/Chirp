package uz.dev.muhammadali.feature.auth.presentation.register_success

data class RegisterSuccessState(
    val registerEmail: String = "",
    val isResendingVerificationEmail: Boolean = false,
)