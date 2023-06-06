package cz.vratislavjindra.nba.common.player.domain

import cz.vratislavjindra.nba.common.architecture.infrastructure.AppError
import cz.vratislavjindra.nba.common.architecture.infrastructure.Result
import cz.vratislavjindra.nba.common.player.model.PlayerDetail
import cz.vratislavjindra.nba.common.player.model.PlayerList
import cz.vratislavjindra.nba.common.team.domain.TeamLogoRepository
import cz.vratislavjindra.nba.common.team.domain.TeamUseCase
import cz.vratislavjindra.nba.common.team.model.Team
import io.kotest.assertions.fail
import io.kotest.matchers.should
import io.kotest.matchers.shouldBe
import io.kotest.matchers.types.shouldBeSameInstanceAs
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Test

internal class PlayerUseCaseTest {

    @Test
    fun `should call repository when getting players list`() = runTest {
        val page = 0
        val playerRepository = mockk<PlayerRepository> {
            coEvery { getPlayers(page = page) } returns Result.success(data = mockk(relaxed = true))
        }
        val getTeamLogo = mockk<TeamUseCase.GetTeamLogo> {}
        PlayerUseCase.GetPlayers(
            repository = playerRepository,
            getTeamLogo = getTeamLogo,
        )(input = page)
        coVerify { playerRepository.getPlayers(page = page) }
    }

    @Test
    fun `should call repository when getting player detail`() = runTest {
        val id = 0
        val teamName = ""
        val playerRepository = mockk<PlayerRepository> {
            coEvery { getPlayerDetail(id = id) } returns Result.success(
                data = mockk(relaxed = true),
            )
        }
        val teamLogoRepository = mockk<TeamLogoRepository> {
            coEvery { getTeamLogoResId(teamName = teamName) } returns null
        }
        PlayerUseCase.GetPlayerDetail(
            repository = playerRepository,
            getTeamLogo = TeamUseCase.GetTeamLogo(repository = teamLogoRepository),
        )(input = id)
        coVerify { playerRepository.getPlayerDetail(id = id) }
    }

    @Test
    fun `should set proper team logo resource id when loading player detail`() = runTest {
        val teamLogoId = 42
        val getPlayerDetailResult = Result.success<PlayerDetail, AppError>(
            data = PlayerDetail(
                id = 0,
                firstName = "firstName",
                lastName = "lastName",
                position = "position",
                heightFeet = null,
                heightInches = null,
                weightPounds = null,
                team = Team(
                    id = 0,
                    abbreviation = "abbreviation",
                    city = "city",
                    conference = "conference",
                    division = "division",
                    fullName = "fullName",
                    name = "name",
                    teamLogoResId = null,
                ),
            ),
        )
        val playerRepository = mockk<PlayerRepository> {
            coEvery { getPlayerDetail(id = any()) } returns getPlayerDetailResult
        }
        val getTeamLogo: TeamUseCase.GetTeamLogo = mockk {
            coEvery { this@mockk.invoke(any()) } answers { teamLogoId }
        }
        val result = PlayerUseCase.GetPlayerDetail(
            repository = playerRepository,
            getTeamLogo = getTeamLogo,
        )(input = 1)
        if (result is Result.Success<PlayerDetail, AppError>) {
            result.data.team.teamLogoResId shouldBe teamLogoId
        } else {
            fail("Result should be success.")
        }
    }
}
