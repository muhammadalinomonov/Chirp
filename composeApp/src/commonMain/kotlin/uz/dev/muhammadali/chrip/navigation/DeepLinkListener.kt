package uz.dev.muhammadali.chrip.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.navigation.NavController
import androidx.navigation.NavUri
import org.jetbrains.compose.ui.tooling.preview.Preview
import uz.dev.muhammadali.core.designsystem.theme.AppTheme

@Composable
fun DeepLinkListener(
    navController: NavController
) {
    DisposableEffect(Unit) {
        ExternalUriHandler.listener = { uri ->
            navController.navigate(deepLink = NavUri(uri))
        }
        onDispose {
            ExternalUriHandler.listener = null
        }
    }
}