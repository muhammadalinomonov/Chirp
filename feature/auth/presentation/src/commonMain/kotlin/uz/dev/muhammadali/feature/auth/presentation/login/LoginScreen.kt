package uz.dev.muhammadali.feature.auth.presentation.login

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import chrip.feature.auth.presentation.generated.resources.Res
import chrip.feature.auth.presentation.generated.resources.create_account
import chrip.feature.auth.presentation.generated.resources.email
import chrip.feature.auth.presentation.generated.resources.email_placeholder
import chrip.feature.auth.presentation.generated.resources.forgot_password
import chrip.feature.auth.presentation.generated.resources.login
import chrip.feature.auth.presentation.generated.resources.password
import chrip.feature.auth.presentation.generated.resources.welcome_back
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.viewmodel.koinViewModel
import uz.dev.muhammadali.core.designsystem.components.brand.ChirpBrandLogo
import uz.dev.muhammadali.core.designsystem.components.buttons.ChirpButton
import uz.dev.muhammadali.core.designsystem.components.buttons.ChirpButtonStyle
import uz.dev.muhammadali.core.designsystem.components.layouts.ChirpAdaptiveFormLayout
import uz.dev.muhammadali.core.designsystem.components.layouts.ChirpSnackbarScaffold
import uz.dev.muhammadali.core.designsystem.components.textfields.ChirpPasswordTextField
import uz.dev.muhammadali.core.designsystem.components.textfields.ChirpTextField
import uz.dev.muhammadali.core.designsystem.theme.AppTheme
import uz.dev.muhammadali.core.presentation.util.ObserveAsEvents

@Composable
fun LoginRoot(
    viewModel: LoginViewModel = koinViewModel(),
    onLoginSuccess: () -> Unit,
    onForgotPasswordClick: () -> Unit,
    onRegisterClick: () -> Unit
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    ObserveAsEvents(viewModel.events) { event ->
        when (event) {
            LoginEvents.Success -> onLoginSuccess()
        }

    }
    LoginScreen(
        state = state,
        onAction = { action ->
            when (action) {
                is LoginAction.OnForgotPasswordClick -> onForgotPasswordClick()
                is LoginAction.OnSignUpClick -> onRegisterClick()
                else -> Unit
            }
            viewModel.onAction(action)
        }
    )
}

@Composable
fun LoginScreen(
    state: LoginState,
    onAction: (LoginAction) -> Unit,
) {
    ChirpSnackbarScaffold(
        snackbarHostState = remember { SnackbarHostState() }
    ) {
        ChirpAdaptiveFormLayout(
            headerText = stringResource(Res.string.welcome_back),
            logo = {
                ChirpBrandLogo()
            },
            errorText = state.error?.asString(),
            modifier = Modifier
                .fillMaxSize()
        ) {
            ChirpTextField(
                state = state.emailTextFieldState,
                placeholder = stringResource(Res.string.email_placeholder),
                keyboardType = KeyboardType.Email,
                singleLine = true,
                modifier = Modifier
                    .fillMaxWidth(),
                title = stringResource(Res.string.email)
            )
            Spacer(modifier = Modifier.height(16.dp))
            ChirpPasswordTextField(
                state = state.passwordTextFieldState,
                title = stringResource(Res.string.password),
                placeholder = stringResource(Res.string.password),
                modifier = Modifier
                    .fillMaxWidth(),
                onVisibilityClick = {
                    onAction(LoginAction.OnTogglePasswordVisibility)
                },
                isPasswordVisible = state.isPasswordVisible,
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = stringResource(Res.string.forgot_password),
                style = MaterialTheme.typography.titleSmall,
                color = MaterialTheme.colorScheme.tertiary,
                modifier = Modifier
                    .align(Alignment.End)
                    .clickable {
                        onAction(LoginAction.OnForgotPasswordClick)
                    }
            )
            Spacer(modifier = Modifier.height(24.dp))
            ChirpButton(
                text = stringResource(Res.string.login),
                onClick = {
                    onAction(LoginAction.OnLoginClick)
                },
                enabled = state.canLogin,
                isLoading = state.isLoggingIn,
                modifier = Modifier
                    .fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(8.dp))
            ChirpButton(
                text = stringResource(Res.string.create_account),
                onClick = {
                    onAction(LoginAction.OnSignUpClick)
                },
                style = ChirpButtonStyle.SECONDARY,
                modifier = Modifier
                    .fillMaxWidth()
            )
        }

    }
}

@Preview
@Composable
private fun Preview() {
    AppTheme {
        LoginScreen(
            state = LoginState(),
            onAction = {}
        )
    }
}

@Preview
@Composable
private fun DarkThemePreview() {
    AppTheme(darkTheme = true) {
        LoginScreen(
            state = LoginState(),
            onAction = {}
        )
    }
}