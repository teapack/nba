package cz.vratislavjindra.nba.common.team.data

import cz.vratislavjindra.nba.common.architecture.infrastructure.AppError
import cz.vratislavjindra.nba.common.architecture.infrastructure.Result
import cz.vratislavjindra.nba.common.network.data.api.TeamApi
import cz.vratislavjindra.nba.common.network.data.handleResponse
import cz.vratislavjindra.nba.common.team.model.Team
import javax.inject.Inject

class TeamRemoteDataSource @Inject constructor(
    private val mapper: TeamMapper,
    private val api: TeamApi,
) {

    suspend fun getTeamDetail(id: Int): Result<Team, AppError> = api
        .getTeamDetail(id = id)
        .handleResponse { mapper.toDomain(dto = it) }
}
