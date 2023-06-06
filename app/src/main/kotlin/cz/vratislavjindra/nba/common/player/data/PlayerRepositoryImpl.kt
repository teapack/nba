package cz.vratislavjindra.nba.common.player.data

import cz.vratislavjindra.nba.common.architecture.infrastructure.AppError
import cz.vratislavjindra.nba.common.architecture.infrastructure.Result
import cz.vratislavjindra.nba.common.player.domain.PlayerRepository
import cz.vratislavjindra.nba.common.player.model.PlayerDetail
import cz.vratislavjindra.nba.common.player.model.PlayerList
import javax.inject.Inject

internal class PlayerRepositoryImpl @Inject constructor(
    private val remoteDataSource: PlayerRemoteDataSource,
) : PlayerRepository {

    override suspend fun getPlayers(page: Int): Result<PlayerList, AppError> {
        return remoteDataSource.getPlayers(page = page, perPage = PLAYERS_PER_PAGE)
    }

    override suspend fun getPlayerDetail(id: Int): Result<PlayerDetail, AppError> {
        return remoteDataSource.getPlayerDetail(id = id)
    }

    companion object {

        private const val PLAYERS_PER_PAGE = 35
    }
}
