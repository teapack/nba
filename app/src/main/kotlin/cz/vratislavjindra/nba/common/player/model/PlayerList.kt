package cz.vratislavjindra.nba.common.player.model

data class PlayerList(
    val players: List<Player>,
    val totalPages: Int,
    val currentPage: Int,
    val nextPage: Int?,
    val perPage: Int,
    val totalCount: Int,
)
