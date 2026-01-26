package uz.dev.muhammadali.chrip

import androidx.compose.runtime.Composable
import org.jetbrains.compose.ui.tooling.preview.Preview
import uz.dev.muhammadali.chrip.navigation.NavigationRoot
import uz.dev.muhammadali.core.designsystem.theme.AppTheme

@Composable
@Preview
fun App() {
    AppTheme {
        NavigationRoot()
    }
}