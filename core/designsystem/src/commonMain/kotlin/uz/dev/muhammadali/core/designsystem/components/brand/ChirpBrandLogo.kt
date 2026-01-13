package uz.dev.muhammadali.core.designsystem.components.brand

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import chrip.core.designsystem.generated.resources.Res
import chrip.core.designsystem.generated.resources.logo_chirp
import org.jetbrains.compose.resources.vectorResource
import org.jetbrains.compose.ui.tooling.preview.Preview
import uz.dev.muhammadali.core.designsystem.theme.AppTheme

@Composable
fun ChirpBrandLogo(
    modifier: Modifier = Modifier
) {
    Icon(
        imageVector = vectorResource(Res.drawable.logo_chirp),
        contentDescription = null,
        tint = MaterialTheme.colorScheme.primary,
        modifier = modifier
    )
}

@Composable
@Preview
fun ChirpBrandLogoPreview() {
    AppTheme {
        ChirpBrandLogo()
    }
}