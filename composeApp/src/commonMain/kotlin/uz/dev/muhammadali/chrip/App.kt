package uz.dev.muhammadali.chrip

import androidx.compose.runtime.Composable
import org.jetbrains.compose.ui.tooling.preview.Preview
import uz.dev.muhammadali.core.designsystem.theme.AppTheme
import uz.dev.muhammadali.feature.auth.presentation.register.RegisterRoot

@Composable
@Preview
fun App() {
    AppTheme {
        RegisterRoot()
    }
}