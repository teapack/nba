package cz.vratislavjindra.nba.common.team.data

import cz.vratislavjindra.nba.common.network.data.dto.TeamDto
import cz.vratislavjindra.nba.common.team.model.Team
import javax.inject.Inject

class TeamMapper @Inject constructor() {

    fun toDomain(dto: TeamDto): Team = Team(
        id = dto.id,
        abbreviation = dto.abbreviation,
        city = dto.city,
        conference = dto.conference,
        division = dto.division,
        fullName = dto.fullName,
        name = dto.name,
    )
}
