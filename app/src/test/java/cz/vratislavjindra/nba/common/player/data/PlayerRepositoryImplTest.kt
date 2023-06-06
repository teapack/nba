package cz.vratislavjindra.nba.common.player.data

import cz.vratislavjindra.nba.common.architecture.infrastructure.AppError
import cz.vratislavjindra.nba.common.architecture.infrastructure.Result
import cz.vratislavjindra.nba.common.player.model.Player
import cz.vratislavjindra.nba.common.player.model.PlayerList
import io.kotest.assertions.fail
import io.kotest.matchers.shouldBe
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Test

internal class PlayerRepositoryImplTest {

    @Test
    fun `should load players list`() = runTest {
        val page = 0
        val perPage = 35
        val playerListResult: Result<PlayerList, AppError> = Result.success(data = mockk())
        val playerRemoteDataSource = mockk<PlayerRemoteDataSource> {
            coEvery { getPlayers(page = page, perPage = perPage) } returns playerListResult
        }
        val playerRepository = PlayerRepositoryImpl(remoteDataSource = playerRemoteDataSource)
        playerRepository.getPlayers(page = page) shouldBe playerListResult
    }

    @Test
    fun `should load players list as fail result when remote data source returns fail`() = runTest {
        val page = 0
        val perPage = 35
        val playerListResult: Result<PlayerList, AppError> = Result.fail(error = mockk())
        val playerRemoteDataSource = mockk<PlayerRemoteDataSource> {
            coEvery { getPlayers(page = page, perPage = perPage) } returns playerListResult
        }
        val playerRepository = PlayerRepositoryImpl(remoteDataSource = playerRemoteDataSource)
        playerRepository.getPlayers(page = page) shouldBe playerListResult
    }

    @Test
    fun `should load requested number of players`() = runTest {
        val page = 0
        val perPage = 35
        val playerListResult: Result<PlayerList, AppError> = Result.success(
            data = PlayerList(
                players = List(
                    size = perPage,
                    init = {
                        Player(
                            id = it,
                            firstName = "firstName",
                            lastName = "lastName",
                            position = "position",
                            teamName = "teamName",
                        )
                    },
                ),
                totalPages = 5,
                currentPage = page,
                nextPage = page + 1,
                perPage = perPage,
                totalCount = 500,
            ),
        )
        val playerRemoteDataSource = mockk<PlayerRemoteDataSource> {
            coEvery { getPlayers(page = page, perPage = perPage) } returns playerListResult
        }
        val playerRepository = PlayerRepositoryImpl(remoteDataSource = playerRemoteDataSource)
        val result = playerRepository.getPlayers(page = page)
        if (result is Result.Success<PlayerList, AppError>) {
            result.data.perPage shouldBe perPage
        } else {
            fail("Result should be success.")
        }
    }
}
