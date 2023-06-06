package cz.vratislavjindra.nba.common.team.data

import cz.vratislavjindra.nba.common.architecture.infrastructure.AppError
import cz.vratislavjindra.nba.common.architecture.infrastructure.Result
import cz.vratislavjindra.nba.common.team.domain.TeamRepository
import cz.vratislavjindra.nba.common.team.model.Team
import javax.inject.Inject

class TeamRepositoryImpl @Inject constructor(
    private val remoteDataSource: TeamRemoteDataSource,
) : TeamRepository {

    override suspend fun getTeamDetail(id: Int): Result<Team, AppError> {
        return remoteDataSource.getTeamDetail(id = id)
    }
}
