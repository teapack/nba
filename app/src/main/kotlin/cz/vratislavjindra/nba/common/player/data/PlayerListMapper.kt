package cz.vratislavjindra.nba.common.player.data

import cz.vratislavjindra.nba.common.network.data.dto.PlayersDto
import cz.vratislavjindra.nba.common.player.model.PlayerList
import javax.inject.Inject

class PlayerListMapper @Inject constructor(
    private val playerMapper: PlayerMapper,
) {

    fun toDomain(dto: PlayersDto): PlayerList = PlayerList(
        players = dto.data.map { playerMapper.toDomain(dto = it) },
        totalPages = dto.meta.totalPages,
        currentPage = dto.meta.currentPage,
        nextPage = dto.meta.nextPage,
        perPage = dto.meta.perPage,
        totalCount = dto.meta.totalCount,
    )
}
