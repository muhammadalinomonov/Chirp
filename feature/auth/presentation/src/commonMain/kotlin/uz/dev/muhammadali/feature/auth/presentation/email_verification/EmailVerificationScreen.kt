package uz.dev.muhammadali.feature.auth.presentation.email_verification

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import chrip.feature.auth.presentation.generated.resources.Res
import chrip.feature.auth.presentation.generated.resources.close
import chrip.feature.auth.presentation.generated.resources.email_verified_failed
import chrip.feature.auth.presentation.generated.resources.email_verified_failed_description
import chrip.feature.auth.presentation.generated.resources.email_verified_successfully
import chrip.feature.auth.presentation.generated.resources.email_verified_successfully_description
import chrip.feature.auth.presentation.generated.resources.login
import chrip.feature.auth.presentation.generated.resources.verifying_account
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.viewmodel.koinViewModel
import uz.dev.muhammadali.core.designsystem.components.brand.ChirpFailureIcon
import uz.dev.muhammadali.core.designsystem.components.brand.ChirpSuccessIcon
import uz.dev.muhammadali.core.designsystem.components.buttons.ChirpButton
import uz.dev.muhammadali.core.designsystem.components.buttons.ChirpButtonStyle
import uz.dev.muhammadali.core.designsystem.components.layouts.ChirpAdaptiveResultLayout
import uz.dev.muhammadali.core.designsystem.components.layouts.ChirpSimpleResultLayout
import uz.dev.muhammadali.core.designsystem.theme.AppTheme
import uz.dev.muhammadali.core.designsystem.theme.extended

@Composable
fun EmailVerificationRoot(
    viewModel: EmailVerificationViewModel = koinViewModel()
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    EmailVerificationScreen(
        state = state,
        onAction = viewModel::onAction
    )
}

@Composable
fun EmailVerificationScreen(
    state: EmailVerificationState,
    onAction: (EmailVerificationAction) -> Unit,
) {
    ChirpAdaptiveResultLayout {
        when {
            state.isVerifying -> {
                VerifyingContent(
                    modifier = Modifier
                        .fillMaxWidth()
                )
            }

            state.isVerified -> {
                ChirpSimpleResultLayout(
                    title = stringResource(Res.string.email_verified_successfully),
                    description = stringResource(Res.string.email_verified_successfully_description),
                    icon = {
                        ChirpSuccessIcon()
                    },
                    primaryButton = {
                        ChirpButton(
                            text = stringResource(Res.string.close),
                            modifier = Modifier
                                .fillMaxWidth(),
                            onClick = {
                                onAction(EmailVerificationAction.OnCloseClick)
                            },
                            style = ChirpButtonStyle.SECONDARY
                        )
                    }
                )
            }

            else -> {
                ChirpSimpleResultLayout(
                    title = stringResource(Res.string.email_verified_failed),
                    description = stringResource(Res.string.email_verified_failed_description),
                    icon = {
                        Spacer(modifier = Modifier.height(32.dp))
                        ChirpFailureIcon(
                            modifier = Modifier
                                .size(80.dp)
                        )
                        Spacer(modifier = Modifier.height(32.dp))
                    },
                    primaryButton = {
                        ChirpButton(
                            text = stringResource(Res.string.login),
                            modifier = Modifier
                                .fillMaxWidth(),
                            onClick = {
                                onAction(EmailVerificationAction.OnLoginClick)
                            }
                        )
                    }
                )
            }

        }

    }
}


@Composable
private fun VerifyingContent(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .heightIn(min = 200.dp)
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(
            16.dp,
            Alignment.CenterVertically
        ),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        CircularProgressIndicator(
            modifier = Modifier
                .size(64.dp),
            color = MaterialTheme.colorScheme.primary
        )
        Text(
            text = stringResource(Res.string.verifying_account),
            color = MaterialTheme.colorScheme.extended.textSecondary,
            style = MaterialTheme.typography.bodySmall
        )
    }
}

@Preview
@Composable
private fun EmailVerificationErrorPreview() {
    AppTheme {
        EmailVerificationScreen(
            state = EmailVerificationState(
                isVerified = false,
                isVerifying = false
            ),
            onAction = {}
        )
    }
}

@Preview
@Composable
private fun EmailVerificationSuccessPreview() {
    AppTheme {
        EmailVerificationScreen(
            state = EmailVerificationState(
                isVerified = true,
                isVerifying = false
            ),
            onAction = {}
        )
    }
}

@Preview
@Composable
private fun EmailVerificationVerifyingPreview() {
    AppTheme {
        EmailVerificationScreen(
            state = EmailVerificationState(
                isVerified = true,
                isVerifying = true
            ),
            onAction = {}
        )
    }
}


