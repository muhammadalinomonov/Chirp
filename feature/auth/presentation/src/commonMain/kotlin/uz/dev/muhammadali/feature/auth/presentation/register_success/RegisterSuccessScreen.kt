package uz.dev.muhammadali.feature.auth.presentation.register_success

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import chrip.feature.auth.presentation.generated.resources.Res
import chrip.feature.auth.presentation.generated.resources.account_successfully_created
import chrip.feature.auth.presentation.generated.resources.login
import chrip.feature.auth.presentation.generated.resources.resend_verification_email
import chrip.feature.auth.presentation.generated.resources.verification_email_send_to_x
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.viewmodel.koinViewModel
import uz.dev.muhammadali.core.designsystem.components.brand.ChirpSuccessIcon
import uz.dev.muhammadali.core.designsystem.components.buttons.ChirpButton
import uz.dev.muhammadali.core.designsystem.components.buttons.ChirpButtonStyle
import uz.dev.muhammadali.core.designsystem.components.layouts.ChirpAdaptiveResultLayout
import uz.dev.muhammadali.core.designsystem.components.layouts.ChirpSimpleSuccessLayout
import uz.dev.muhammadali.core.designsystem.theme.AppTheme

@Composable
fun RegisterSuccessRoot(
    viewModel: RegisterSuccessViewModel = koinViewModel()
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    RegisterSuccessScreen(
        state = state,
        onAction = viewModel::onAction
    )
}

@Composable
fun RegisterSuccessScreen(
    state: RegisterSuccessState,
    onAction: (RegisterSuccessAction) -> Unit,
) {
    ChirpAdaptiveResultLayout {
        ChirpSimpleSuccessLayout(
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
            }
        )
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