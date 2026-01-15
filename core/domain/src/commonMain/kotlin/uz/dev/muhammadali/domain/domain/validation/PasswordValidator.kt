package uz.dev.muhammadali.domain.domain.validation

object PasswordValidator {
    private const val MIN_PASSWORD_LENGTH = 9

    fun validatePassword(password: String): PasswordValidationState {
        return PasswordValidationState(
            hasMinLength = password.length >= MIN_PASSWORD_LENGTH,
            hasDigit = password.any { it.isDigit() },
            hasUpperCase = password.any { it.isUpperCase() }
        )
    }
}