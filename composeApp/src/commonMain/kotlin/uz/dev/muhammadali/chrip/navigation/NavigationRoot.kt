package uz.dev.muhammadali.chrip.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import uz.dev.muhammadali.feature.auth.presentation.navigation.AuthGraphRoutes
import uz.dev.muhammadali.feature.auth.presentation.navigation.authGraph
import uz.dev.muhammadali.feature.chat.presentation.chat_list.ChatListRoute
import uz.dev.muhammadali.feature.chat.presentation.chat_list.ChatListScreenRoot

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
                navController.navigate(ChatListRoute) {
                    popUpTo(AuthGraphRoutes.Graph) {
                        inclusive = true
                    }
                }
            }
        )
        composable<ChatListRoute> {
            ChatListScreenRoot()
        }
    }
}
