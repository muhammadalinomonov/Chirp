package uz.dev.muhammadali.core.designsystem.preview

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.tooling.preview.PreviewScreenSizes
import org.jetbrains.compose.ui.tooling.preview.Preview
import uz.dev.muhammadali.core.designsystem.components.brand.ChirpBrandLogo
import uz.dev.muhammadali.core.designsystem.components.layouts.ChirpAdaptiveFormLayout
import uz.dev.muhammadali.core.designsystem.components.layouts.ChirpAdaptiveResultLayout
import uz.dev.muhammadali.core.designsystem.theme.AppTheme

@Composable
@PreviewLightDark
@PreviewScreenSizes
fun ChirpAdaptiveResultLayoutPreview() {
    AppTheme {
        ChirpAdaptiveResultLayout(
            modifier = Modifier.fillMaxSize(),
            content = {
                Text(
                    text = "Registration successful!",
                    style = MaterialTheme.typography.titleLarge,
                    color = MaterialTheme.colorScheme.onSurface
                )
            }
        )
    }
}