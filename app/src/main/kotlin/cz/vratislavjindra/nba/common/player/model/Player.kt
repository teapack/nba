package cz.vratislavjindra.nba.common.player.model

data class Player(
    val id: Int,
    val firstName: String,
    val lastName: String,
    val position: String,
    val teamName: String,
    val teamLogoResId: Int? = null,
) {

    fun fullName(): String = "$firstName $lastName"
}
