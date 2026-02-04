package uz.dev.muhammadali.chrip.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import uz.dev.muhammadali.feature.auth.presentation.navigation.AuthGraphRoutes
import uz.dev.muhammadali.feature.auth.presentation.navigation.authGraph

@Composable
fun NavigationRoot(
    navController: NavHostController
) {
    NavHost(
        navController = navController,
        startDestination = AuthGraphRoutes.Graph,
    ) {
        authGraph(
            navController = navController,
            onLoginSuccess = {

            }
        )
    }
}
