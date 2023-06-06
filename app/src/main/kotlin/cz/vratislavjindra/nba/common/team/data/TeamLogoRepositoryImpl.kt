package cz.vratislavjindra.nba.common.team.data

import cz.vratislavjindra.nba.R
import cz.vratislavjindra.nba.common.team.domain.TeamLogoRepository

class TeamLogoRepositoryImpl : TeamLogoRepository {

    override suspend fun getTeamLogoResId(teamName: String): Int? =
        teamLogoMap[teamName.lowercase().substringAfterLast(delimiter = ' ')]

    private companion object {

        val teamLogoMap: Map<String, Int> = hashMapOf(
            Pair(first = "76ers", second = R.drawable.logo_76ers),
            Pair(first = "blazers", second = R.drawable.logo_blazers),
            Pair(first = "bucks", second = R.drawable.logo_bucks),
            Pair(first = "bulls", second = R.drawable.logo_bulls),
            Pair(first = "cavaliers", second = R.drawable.logo_cavaliers),
            Pair(first = "celtics", second = R.drawable.logo_celtics),
            Pair(first = "clippers", second = R.drawable.logo_clippers),
            Pair(first = "grizzlies", second = R.drawable.logo_grizzlies),
            Pair(first = "hawks", second = R.drawable.logo_hawks),
            Pair(first = "heat", second = R.drawable.logo_heat),
            Pair(first = "hornets", second = R.drawable.logo_hornets),
            Pair(first = "jazz", second = R.drawable.logo_jazz),
            Pair(first = "kings", second = R.drawable.logo_kings),
            Pair(first = "knicks", second = R.drawable.logo_knicks),
            Pair(first = "lakers", second = R.drawable.logo_lakers),
            Pair(first = "magic", second = R.drawable.logo_magic),
            Pair(first = "mavericks", second = R.drawable.logo_mavericks),
            Pair(first = "nets", second = R.drawable.logo_nets),
            Pair(first = "nuggets", second = R.drawable.logo_nuggets),
            Pair(first = "pacers", second = R.drawable.logo_pacers),
            Pair(first = "pelicans", second = R.drawable.logo_pelicans),
            Pair(first = "pistons", second = R.drawable.logo_pistons),
            Pair(first = "raptors", second = R.drawable.logo_raptors),
            Pair(first = "rockets", second = R.drawable.logo_rockets),
            Pair(first = "spurs", second = R.drawable.logo_spurs),
            Pair(first = "suns", second = R.drawable.logo_suns),
            Pair(first = "thunder", second = R.drawable.logo_thunder),
            Pair(first = "timberwolves", second = R.drawable.logo_timberwolves),
            Pair(first = "warriors", second = R.drawable.logo_warriors),
            Pair(first = "wizards", second = R.drawable.logo_wizards),
        )
    }
}
