package edu.itvo.roompersistence.presentation.navigation

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.*
import androidx.navigation.navArgument
import edu.itvo.roompersistence.presentation.player.screen.*

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun AppNavGraph() {

    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = PlayerRoutes.PlayerList.route
    ) {

        // =====================
        // PLAYERS
        // =====================
        composable(PlayerRoutes.PlayerList.route) {

            PlayerListScreen(

                onNavigateToAddPlayer = {
                    navController.navigate(PlayerRoutes.AddPlayer.route)
                },

                onNavigateToEditPlayer = { playerId ->
                    navController.navigate(
                        PlayerRoutes.EditPlayer.createRoute(playerId)
                    )
                },

                onNavigateToStadiums = {
                    navController.navigate("stadiums")
                }
            )
        }

        // =====================
        // ADD PLAYER
        // =====================
        composable(PlayerRoutes.AddPlayer.route) {
            AddPlayerScreen(
                playerId = null,
                onNavigateBack = { navController.popBackStack() }
            )
        }

        // =====================
        // EDIT PLAYER
        // =====================
        composable(
            route = PlayerRoutes.EditPlayer.route,
            arguments = listOf(
                navArgument("playerId") {
                    type = NavType.LongType
                }
            )
        ) { backStackEntry ->

            val playerId = backStackEntry.arguments?.getLong("playerId")

            AddPlayerScreen(
                playerId = playerId,
                onNavigateBack = { navController.popBackStack() }
            )
        }

        // =====================
        // STADIUMS
        // =====================
        composable("stadiums") {
            StadiumScreen()
        }
    }
}