package uz.dev.muhammadali.domain.domain.validation

data class PasswordValidationState(
    val hasMinLength: Boolean = false,
    val hasDigit: Boolean = false,
    val hasUpperCase: Boolean = false,
) {
    val isValidPassword: Boolean
        get() = hasMinLength && hasDigit && hasUpperCase
}
