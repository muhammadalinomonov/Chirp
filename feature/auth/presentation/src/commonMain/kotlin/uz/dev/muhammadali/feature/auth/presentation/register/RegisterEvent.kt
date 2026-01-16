package uz.dev.muhammadali.feature.auth.presentation.register

sealed interface RegisterEvent {
    data class Success(val email: String) : RegisterEvent
}