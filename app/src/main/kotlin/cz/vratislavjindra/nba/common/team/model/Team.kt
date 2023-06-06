package cz.vratislavjindra.nba.common.team.model

data class Team(
    val id: Int,
    val abbreviation: String,
    val city: String,
    val conference: String,
    val division: String,
    val fullName: String,
    val name: String,
    val teamLogoResId: Int? = null,
)
