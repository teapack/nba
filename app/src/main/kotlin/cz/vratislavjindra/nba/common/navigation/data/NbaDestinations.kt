package cz.vratislavjindra.nba.common.navigation.data

import androidx.navigation.NavHostController

/**
 * Destinations used in the app.
 */
sealed class NbaDestinations(val route: String) {

    object PlayerList : NbaDestinations(route = "players")

    sealed class WithArgument(
        route: String,
        val navArgumentName: String,
    ) : NbaDestinations(route = route) {

        object PlayerDetail : WithArgument(
            route = "player",
            navArgumentName = "player_id",
        )

        object TeamDetail : WithArgument(
            route = "team",
            navArgumentName = "team_id",
        )
    }
}

/**
 * Models the navigation actions in the app.
 */
class NbaNavigationActions(navController: NavHostController) {
    val navigateToPlayerDetail: (playerId: Int) -> Unit = { playerId ->
        navController.navigate(
            route = "${NbaDestinations.WithArgument.PlayerDetail.route}/$playerId",
        )
    }
    val navigateToTeamDetail: (teamId: Int) -> Unit = { teamId ->
        navController.navigate(route = "${NbaDestinations.WithArgument.TeamDetail.route}/$teamId")
    }
    val navigateBack: () -> Unit = { navController.popBackStack() }
}
