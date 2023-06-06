package cz.vratislavjindra.nba.common.team.domain

import cz.vratislavjindra.nba.common.architecture.infrastructure.AppError
import cz.vratislavjindra.nba.common.architecture.infrastructure.Result
import cz.vratislavjindra.nba.common.architecture.infrastructure.mapSuccess
import cz.vratislavjindra.nba.common.coroutines.domain.SuspendUseCase
import cz.vratislavjindra.nba.common.team.model.Team
import javax.inject.Inject

sealed interface TeamUseCase {

    class GetTeamDetail @Inject constructor(
        private val repository: TeamRepository,
        private val getTeamLogo: GetTeamLogo,
    ) : SuspendUseCase<Int, Result<Team, AppError>>() {

        override suspend fun invoke(input: Int): Result<Team, AppError> = repository
            .getTeamDetail(id = input)
            .mapSuccess { team -> team.copy(teamLogoResId = getTeamLogo(input = team.name)) }
    }

    class GetTeamLogo @Inject constructor(
        private val repository: TeamLogoRepository,
    ) : SuspendUseCase<String, Int?>() {

        override suspend fun invoke(input: String): Int? =
            repository.getTeamLogoResId(teamName = input)
    }
}
