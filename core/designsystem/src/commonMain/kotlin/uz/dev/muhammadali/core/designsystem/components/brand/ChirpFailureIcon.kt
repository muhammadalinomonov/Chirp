package uz.dev.muhammadali.core.designsystem.components.brand

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
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
fun ChirpFailureIcon(
    modifier: Modifier = Modifier
) {
    Icon(
        imageVector = Icons.Default.Close,
        contentDescription = null,
        tint = MaterialTheme.colorScheme.error,
        modifier = modifier
    )
}

@Composable
@Preview
fun ChirpFailureIconPreview() {
    AppTheme {
        ChirpSuccessIcon()
    }
}