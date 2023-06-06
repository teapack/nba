package cz.vratislavjindra.nba.common.player.data

import cz.vratislavjindra.nba.common.network.data.dto.PlayerDto
import cz.vratislavjindra.nba.common.player.model.PlayerDetail
import cz.vratislavjindra.nba.common.team.data.TeamMapper
import javax.inject.Inject

class PlayerDetailMapper @Inject constructor(
    private val teamMapper: TeamMapper,
) {

    fun toDomain(
        dto: PlayerDto,
    ): PlayerDetail = PlayerDetail(
        id = dto.id,
        firstName = dto.firstName,
        lastName = dto.lastName,
        position = dto.position,
        heightFeet = dto.heightFeet,
        heightInches = dto.heightInches,
        weightPounds = dto.weightPounds,
        team = teamMapper.toDomain(dto = dto.team),
    )
}
