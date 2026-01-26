package uz.dev.muhammadali.chrip.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import uz.dev.muhammadali.feature.auth.presentation.navigation.AuthGraphRoutes
import uz.dev.muhammadali.feature.auth.presentation.navigation.authGraph

@Composable
fun NavigationRoot() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = AuthGraphRoutes.Graph,
    ) {
        authGraph(
            navController = navController, onLoginSuccess = {

            }
        )
    }
}
