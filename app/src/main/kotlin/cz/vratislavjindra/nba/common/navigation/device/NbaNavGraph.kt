package cz.vratislavjindra.nba.common.navigation.device

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import cz.vratislavjindra.nba.common.navigation.data.NbaDestinations
import cz.vratislavjindra.nba.common.navigation.data.NbaNavigationActions
import cz.vratislavjindra.nba.feature.player.presentation.PlayerDetailScreen
import cz.vratislavjindra.nba.feature.player.presentation.PlayerListScreen
import cz.vratislavjindra.nba.feature.team.detail.presentation.TeamDetailScreen

@Composable
fun NbaNavGraph(
    navController: NavHostController,
    navigationActions: NbaNavigationActions,
) {
    NavHost(
        navController = navController,
        startDestination = NbaDestinations.PlayerList.route,
    ) {
        composable(route = NbaDestinations.PlayerList.route) {
            PlayerListScreen(navigateToPlayerDetail = navigationActions.navigateToPlayerDetail)
        }
        composable(
            route = "${
                NbaDestinations.WithArgument.PlayerDetail.route
            }/{${
                NbaDestinations.WithArgument.PlayerDetail.navArgumentName
            }}",
            arguments = listOf(
                navArgument(name = NbaDestinations.WithArgument.PlayerDetail.navArgumentName) {
                    type = NavType.IntType
                },
            ),
        ) {
            PlayerDetailScreen(
                navigateToTeamDetail = navigationActions.navigateToTeamDetail,
                navigateBack = navigationActions.navigateBack,
            )
        }
        composable(
            route = "${NbaDestinations.WithArgument.TeamDetail.route}/{${
                NbaDestinations.WithArgument.TeamDetail.navArgumentName
            }}",
            arguments = listOf(
                navArgument(name = NbaDestinations.WithArgument.TeamDetail.navArgumentName) {
                    type = NavType.IntType
                },
            ),
        ) { TeamDetailScreen(navigateBack = navigationActions.navigateBack) }
    }
}
