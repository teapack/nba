package cz.vratislavjindra.nba.common.player.model

import cz.vratislavjindra.nba.common.team.model.Team

data class PlayerDetail(
    val id: Int,
    val firstName: String,
    val lastName: String,
    val position: String,
    val heightFeet: Int?,
    val heightInches: Int?,
    val weightPounds: Int?,
    val team: Team,
) {

    fun fullName(): String = "$firstName $lastName"
}
