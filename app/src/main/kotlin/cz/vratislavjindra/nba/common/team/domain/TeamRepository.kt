package cz.vratislavjindra.nba.common.team.domain

import cz.vratislavjindra.nba.common.architecture.infrastructure.AppError
import cz.vratislavjindra.nba.common.architecture.infrastructure.Result
import cz.vratislavjindra.nba.common.team.model.Team

interface TeamRepository {

    suspend fun getTeamDetail(id: Int): Result<Team, AppError>
}
