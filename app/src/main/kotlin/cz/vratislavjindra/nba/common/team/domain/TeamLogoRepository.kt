package cz.vratislavjindra.nba.common.team.domain

interface TeamLogoRepository {

    suspend fun getTeamLogoResId(teamName: String): Int?
}
