package uz.dev.muhammadali.feature.auth.presentation.register

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import org.jetbrains.compose.ui.tooling.preview.Preview
import uz.dev.muhammadali.core.designsystem.theme.AppTheme

@Composable
fun RegisterRoot(
    viewModel: RegisterViewModel = viewModel()
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    RegisterScreen(
        state = state,
        onAction = viewModel::onAction
    )
}

@Composable
fun RegisterScreen(
    state: RegisterState,
    onAction: (RegisterAction) -> Unit,
) {

}

@Preview
@Composable
private fun Preview() {
    AppTheme {
        RegisterScreen(
            state = RegisterState(),
            onAction = {}
        )
    }
}