package uz.dev.muhammadali.feature.auth.presentation.login

sealed interface LoginEvents {
    data object Success: LoginEvents

}