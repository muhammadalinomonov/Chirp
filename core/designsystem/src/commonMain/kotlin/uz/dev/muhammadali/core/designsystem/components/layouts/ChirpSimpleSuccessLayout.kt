package uz.dev.muhammadali.core.designsystem.components.layouts

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import org.jetbrains.compose.ui.tooling.preview.Preview
import uz.dev.muhammadali.core.designsystem.components.brand.ChirpSuccessIcon
import uz.dev.muhammadali.core.designsystem.components.buttons.ChirpButton
import uz.dev.muhammadali.core.designsystem.components.buttons.ChirpButtonStyle
import uz.dev.muhammadali.core.designsystem.theme.AppTheme
import uz.dev.muhammadali.core.designsystem.theme.extended

@Composable
fun ChirpSimpleSuccessLayout(
    title: String,
    description: String,
    modifier: Modifier = Modifier,
    secondaryButton: @Composable (() -> Unit)? = null,
    icon: @Composable () -> Unit,
    secondaryError: String? = null,
    primaryButton: @Composable () -> Unit
) {
    Column(
        modifier = modifier
            .padding(horizontal = 32.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        icon()
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .offset(y = (-25).dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Text(
                text = title,
                style = MaterialTheme.typography.titleLarge,
                color = MaterialTheme.colorScheme.extended.textPrimary,
                textAlign = TextAlign.Center
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = description,
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.extended.textSecondary,
                textAlign = TextAlign.Center
            )
            Spacer(modifier = Modifier.height(24.dp))
            primaryButton()

            secondaryButton?.let {
                Spacer(modifier = Modifier.height(8.dp))
                it()
                if (secondaryError != null) {
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = secondaryError,
                        modifier = Modifier
                            .fillMaxWidth(),
                        style = MaterialTheme.typography.labelSmall,
                        color = MaterialTheme.colorScheme.error,
                        textAlign = TextAlign.Center
                    )
                }
            }
            Spacer(modifier = Modifier.height(8.dp))
        }
    }
}

@Composable
@Preview
fun ChirpSimpleSuccessLayoutPreview() {
    AppTheme(darkTheme = true) {
        ChirpSimpleSuccessLayout(
            title = "Chirp successfully created!",
            description = "We’ve sent verification email to olivia@chirp.chat",
            icon = {
                ChirpSuccessIcon()
            },
            primaryButton = {
                ChirpButton(
                    text = "Log in",
                    onClick = {},
                    modifier = Modifier.fillMaxWidth()
                )
            },
            secondaryButton = {
                ChirpButton(
                    text = "Resend verification email",
                    onClick = {},
                    style = ChirpButtonStyle.SECONDARY,
                    modifier = Modifier.fillMaxWidth()
                )
            }
        )
    }
}