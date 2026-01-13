package uz.dev.muhammadali.core.designsystem.components.brand

import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import chrip.core.designsystem.generated.resources.Res
import chrip.core.designsystem.generated.resources.logo_chirp
import chrip.core.designsystem.generated.resources.success_checkmark
import org.jetbrains.compose.resources.vectorResource
import org.jetbrains.compose.ui.tooling.preview.Preview
import uz.dev.muhammadali.core.designsystem.theme.AppTheme
import uz.dev.muhammadali.core.designsystem.theme.extended

@Composable
fun ChirpSuccessIcon(
    modifier: Modifier = Modifier
) {
    Icon(
        imageVector = vectorResource(Res.drawable.success_checkmark),
        contentDescription = null,
        tint = MaterialTheme.colorScheme.extended.success,
        modifier = modifier
    )
}

@Composable
@Preview
fun ChirpSuccessIconPreview() {
    AppTheme {
        ChirpSuccessIcon()
    }
}