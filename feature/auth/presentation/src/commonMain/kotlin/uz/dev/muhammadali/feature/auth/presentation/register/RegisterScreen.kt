package uz.dev.muhammadali.feature.auth.presentation.register

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import chrip.feature.auth.presentation.generated.resources.Res
import chrip.feature.auth.presentation.generated.resources.email
import chrip.feature.auth.presentation.generated.resources.email_placeholder
import chrip.feature.auth.presentation.generated.resources.login
import chrip.feature.auth.presentation.generated.resources.password
import chrip.feature.auth.presentation.generated.resources.password_hint
import chrip.feature.auth.presentation.generated.resources.register
import chrip.feature.auth.presentation.generated.resources.username
import chrip.feature.auth.presentation.generated.resources.username_hint
import chrip.feature.auth.presentation.generated.resources.username_placeholder
import chrip.feature.auth.presentation.generated.resources.welcome_to_chirp
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview
import uz.dev.muhammadali.core.designsystem.components.brand.ChirpBrandLogo
import uz.dev.muhammadali.core.designsystem.components.buttons.ChirpButton
import uz.dev.muhammadali.core.designsystem.components.buttons.ChirpButtonStyle
import uz.dev.muhammadali.core.designsystem.components.layouts.ChirpAdaptiveFormLayout
import uz.dev.muhammadali.core.designsystem.components.layouts.ChirpSnackbarScaffold
import uz.dev.muhammadali.core.designsystem.components.textfields.ChirpPasswordTextField
import uz.dev.muhammadali.core.designsystem.components.textfields.ChirpTextField
import uz.dev.muhammadali.core.designsystem.theme.AppTheme

@Composable
fun RegisterRoot(
    viewModel: RegisterViewModel = viewModel()
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    val snackbarState = remember { SnackbarHostState() }
    RegisterScreen(
        state = state,
        onAction = viewModel::onAction,
        snackbarHostState = snackbarState
    )
}

@Composable
fun RegisterScreen(
    state: RegisterState,
    onAction: (RegisterAction) -> Unit,
    snackbarHostState: SnackbarHostState,
) {
    ChirpSnackbarScaffold(
        snackbarHostState = snackbarHostState,
    ) {
        ChirpAdaptiveFormLayout(
            headerText = stringResource(Res.string.welcome_to_chirp),
            errorText = state.registrationError?.asString(),
            logo = {
                ChirpBrandLogo()
            },
        ) {
            ChirpTextField(
                state = state.usernameTextState,
                placeholder = stringResource(Res.string.username_placeholder),
                supportingText = state.usernameError?.asString()
                    ?: stringResource(Res.string.username_hint),
                title = stringResource(Res.string.username),
                isError = state.usernameError != null,
                onFocusChanged = { isFocused ->
                    onAction(RegisterAction.OnInputTextFocusGain)
                }
            )
            Spacer(modifier = Modifier.height(20.dp))
            ChirpTextField(
                state = state.emailTextState,
                placeholder = stringResource(Res.string.email_placeholder),
                supportingText = state.emailError?.asString(),
                title = stringResource(Res.string.email),
                isError = state.emailError != null,
                onFocusChanged = { isFocused ->
                    onAction(RegisterAction.OnInputTextFocusGain)
                }
            )

            Spacer(modifier = Modifier.height(20.dp))
            ChirpPasswordTextField(
                state = state.passwordTextState,
                placeholder = stringResource(Res.string.password),
                supportingText = state.passwordError?.asString()
                    ?: stringResource(Res.string.password_hint),
                title = stringResource(Res.string.password),
                isError = state.passwordError != null,
                onFocusChanged = { isFocused ->
                    onAction(RegisterAction.OnInputTextFocusGain)
                },
                onVisibilityClick = {
                    onAction(RegisterAction.OnTogglePasswordVisibilityClick)
                },
                isPasswordVisible = state.isPasswordVisible,
            )
            Spacer(modifier = Modifier.height(20.dp))
            ChirpButton(
                text = stringResource(Res.string.register),
                enabled = state.canRegister,
                isLoading = state.isRegistering,
                modifier = Modifier.fillMaxWidth(),
                onClick = {
                    onAction(RegisterAction.OnRegisterClick)
                }
            )
            Spacer(modifier = Modifier.height(8.dp))
            ChirpButton(
                text = stringResource(Res.string.login),
                modifier = Modifier.fillMaxWidth(),
                style = ChirpButtonStyle.SECONDARY,
                onClick = {
                    onAction(RegisterAction.OnLoginClick)
                }
            )


        }
    }
}

@Preview
@Composable
private fun Preview() {
    AppTheme {
        RegisterScreen(
            state = RegisterState(),
            onAction = {},
            snackbarHostState = remember { SnackbarHostState() }
        )
    }
}