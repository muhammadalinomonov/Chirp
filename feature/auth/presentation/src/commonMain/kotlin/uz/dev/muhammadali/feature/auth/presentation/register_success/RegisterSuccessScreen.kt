package uz.dev.muhammadali.feature.auth.presentation.register_success

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import chrip.feature.auth.presentation.generated.resources.Res
import chrip.feature.auth.presentation.generated.resources.account_successfully_created
import chrip.feature.auth.presentation.generated.resources.login
import chrip.feature.auth.presentation.generated.resources.resend_verification_email
import chrip.feature.auth.presentation.generated.resources.verification_email_send_to_x
import chrip.feature.auth.presentation.generated.resources.verification_email_successfully_resent
import org.jetbrains.compose.resources.getString
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.viewmodel.koinViewModel
import uz.dev.muhammadali.core.designsystem.components.brand.ChirpSuccessIcon
import uz.dev.muhammadali.core.designsystem.components.buttons.ChirpButton
import uz.dev.muhammadali.core.designsystem.components.buttons.ChirpButtonStyle
import uz.dev.muhammadali.core.designsystem.components.layouts.ChirpAdaptiveResultLayout
import uz.dev.muhammadali.core.designsystem.components.layouts.ChirpSimpleResultLayout
import uz.dev.muhammadali.core.designsystem.components.layouts.ChirpSnackbarScaffold
import uz.dev.muhammadali.core.designsystem.theme.AppTheme
import uz.dev.muhammadali.core.presentation.util.ObserveAsEvents

@Composable
fun RegisterSuccessRoot(
    viewModel: RegisterSuccessViewModel = koinViewModel(),
    onLoginClick: () -> Unit
) {
    val state by viewModel.state.collectAsStateWithLifecycle()


    val snackbarHostState = remember { SnackbarHostState() }
    ObserveAsEvents(viewModel.events) { event ->
        when (event) {
            is RegisterSuccessEvent.ResendVerificationEmailSuccess -> {
                snackbarHostState.showSnackbar(
                    message = getString(
                        resource = Res.string.verification_email_successfully_resent
                    )
                )
            }
        }
    }
    RegisterSuccessScreen(
        state = state,
        onAction = {
            when (it) {
                is RegisterSuccessAction.OnLoginClick -> onLoginClick()
                else -> Unit
            }
            viewModel.onAction(it)
        },
        snackbarHostState = snackbarHostState
    )
}

@Composable
fun RegisterSuccessScreen(
    state: RegisterSuccessState,
    onAction: (RegisterSuccessAction) -> Unit,
    snackbarHostState: SnackbarHostState = remember { SnackbarHostState() }
) {
    ChirpSnackbarScaffold(
        snackbarHostState = snackbarHostState,
    ) {
        ChirpAdaptiveResultLayout {
            ChirpSimpleResultLayout(
                title = stringResource(Res.string.account_successfully_created),
                description = stringResource(
                    Res.string.verification_email_send_to_x,
                    state.registerEmail
                ),
                icon = {
                    ChirpSuccessIcon()
                },
                primaryButton = {
                    ChirpButton(
                        modifier = Modifier
                            .fillMaxWidth(),
                        text = stringResource(Res.string.login),
                        onClick = {
                            onAction(RegisterSuccessAction.OnLoginClick)
                        }
                    )
                },
                secondaryButton = {
                    ChirpButton(
                        modifier = Modifier
                            .fillMaxWidth(),
                        style = ChirpButtonStyle.SECONDARY,
                        text = stringResource(Res.string.resend_verification_email),
                        onClick = {
                            onAction(RegisterSuccessAction.OnResendVerificationEmailClick)
                        },
                        enabled = !state.isResendingVerificationEmail,
                        isLoading = state.isResendingVerificationEmail
                    )
                },
                secondaryError = state.resendVerificationError?.asString()
            )
        }
    }
}

@Preview
@Composable
private fun Preview() {
    AppTheme {
        RegisterSuccessScreen(
            state = RegisterSuccessState(
                registerEmail = "test@gmail.com"
            ),
            onAction = {}
        )
    }
}