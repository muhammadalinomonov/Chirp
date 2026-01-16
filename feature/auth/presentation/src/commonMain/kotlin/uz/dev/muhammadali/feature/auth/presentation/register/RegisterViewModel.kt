package uz.dev.muhammadali.feature.auth.presentation.register

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import chrip.feature.auth.presentation.generated.resources.Res
import chrip.feature.auth.presentation.generated.resources.error_account_already_exist
import chrip.feature.auth.presentation.generated.resources.error_invalid_email
import chrip.feature.auth.presentation.generated.resources.error_invalid_password
import chrip.feature.auth.presentation.generated.resources.error_invalid_username
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import uz.dev.muhammadali.core.presentation.util.UiText
import uz.dev.muhammadali.core.presentation.util.toUiText
import uz.dev.muhammadali.domain.domain.auth.AuthService
import uz.dev.muhammadali.domain.domain.util.DataError
import uz.dev.muhammadali.domain.domain.util.onFailure
import uz.dev.muhammadali.domain.domain.util.onSuccess
import uz.dev.muhammadali.domain.domain.validation.PasswordValidator
import uz.dev.muhammadali.feature.auth.domain.EmailValidator

class RegisterViewModel(
    private val authService: AuthService
) : ViewModel() {
    private val eventChannel = Channel<RegisterEvent>()
    val events = eventChannel.receiveAsFlow()

    private var hasLoadedInitialData = false

    private val _state = MutableStateFlow(RegisterState())
    val state = _state
        .onStart {
            if (!hasLoadedInitialData) {
                /** Load initial data here **/
                hasLoadedInitialData = true
            }
        }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000L),
            initialValue = RegisterState()
        )

    fun onAction(action: RegisterAction) {
        when (action) {
            RegisterAction.OnLoginClick -> validateFormInputs()
            RegisterAction.OnInputTextFocusGain -> clearAllTextFieldsErrors()
            RegisterAction.OnRegisterClick -> register()
            RegisterAction.OnTogglePasswordVisibilityClick -> {
                _state.update {
                    it.copy(
                        isPasswordVisible = !it.isPasswordVisible
                    )
                }
            }
        }
    }

    private fun register() {
        if (!validateFormInputs()) {
            return
        }
        viewModelScope.launch {
            _state.update {
                it.copy(
                    isRegistering = true
                )
            }
            with(state.value) {
                val email = emailTextState.text.toString()
                val username = usernameTextState.text.toString()
                val password = passwordTextState.text.toString()

                authService
                    .register(
                        email = email,
                        username = username,
                        password = password
                    )
                    .onSuccess {
                        _state.update {
                            it.copy(
                                isRegistering = false
                            )
                        }
                    }
                    .onFailure { error ->
                        val registrationError = when (error) {
                            DataError.Remote.CONFLICT -> {
                                UiText.Resource(Res.string.error_account_already_exist)
                            }

                            else -> error.toUiText()
                        }
                        _state.update {
                            it.copy(
                                isRegistering = false,
                                registrationError = registrationError
                            )
                        }
                    }
            }
        }
    }

    private fun clearAllTextFieldsErrors() {
        _state.update {
            it.copy(
                emailError = null,
                passwordError = null,
                usernameError = null,
                registrationError = null,
            )
        }
    }

    fun validateFormInputs(): Boolean {

        clearAllTextFieldsErrors()

        val currentState = state.value
        val email = currentState.emailTextState.text.toString()
        val userName = currentState.usernameTextState.text.toString()
        val password = currentState.passwordTextState.text.toString()

        val isEmailValid = EmailValidator.validate(email)
        val passwordValidationState = PasswordValidator.validatePassword(password)
        val isUsernameValid = userName.length in 3..20

        val emailError = if (!isEmailValid) {
            UiText.Resource(Res.string.error_invalid_email)
        } else null
        val passwordError = if (!passwordValidationState.isValidPassword) {
            UiText.Resource(Res.string.error_invalid_password)
        } else null

        val usernameError = if (!isUsernameValid) {
            UiText.Resource(Res.string.error_invalid_username)
        } else null

        _state.update {
            it.copy(
                emailError = emailError,
                passwordError = passwordError,
                usernameError = usernameError
            )
        }

        return isUsernameValid && isEmailValid && passwordValidationState.isValidPassword
    }

}

