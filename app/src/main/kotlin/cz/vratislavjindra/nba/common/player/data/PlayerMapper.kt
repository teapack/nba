package cz.vratislavjindra.nba.common.player.data

import cz.vratislavjindra.nba.common.network.data.dto.PlayerDto
import cz.vratislavjindra.nba.common.player.model.Player
import javax.inject.Inject

class PlayerMapper @Inject constructor() {

    fun toDomain(dto: PlayerDto): Player = Player(
        id = dto.id,
        firstName = dto.firstName,
        lastName = dto.lastName,
        position = dto.position,
        teamName = dto.team.name,
    )
}
