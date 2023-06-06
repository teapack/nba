package cz.vratislavjindra.nba.common.team.domain

import cz.vratislavjindra.nba.common.architecture.infrastructure.AppError
import cz.vratislavjindra.nba.common.architecture.infrastructure.Result
import cz.vratislavjindra.nba.common.team.model.Team
import io.kotest.assertions.fail
import io.kotest.matchers.shouldBe
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Test

internal class TeamUseCaseTest {

    @Test
    fun `should call repository when getting team detail`() = runTest {
        val id = 0
        val teamName = ""
        val teamRepository = mockk<TeamRepository> {
            coEvery { getTeamDetail(id = any()) } returns Result.success(
                data = mockk(relaxed = true),
            )
        }
        val teamLogoRepository = mockk<TeamLogoRepository> {
            coEvery { getTeamLogoResId(teamName = teamName) } returns null
        }
        TeamUseCase.GetTeamDetail(
            repository = teamRepository,
            getTeamLogo = TeamUseCase.GetTeamLogo(repository = teamLogoRepository),
        )(input = id)
        coVerify { teamRepository.getTeamDetail(id = id) }
    }

    @Test
    fun `should set proper team logo resource id when loading team detail`() = runTest {
        val teamLogoId = 69
        val getTeamDetailResult = Result.success<Team, AppError>(
            data = Team(
                id = 0,
                abbreviation = "abbreviation",
                city = "city",
                conference = "conference",
                division = "division",
                fullName = "fullName",
                name = "name",
                teamLogoResId = null,
            ),
        )
        val teamRepository = mockk<TeamRepository> {
            coEvery { getTeamDetail(id = any()) } returns getTeamDetailResult
        }
        val getTeamLogo: TeamUseCase.GetTeamLogo = mockk {
            coEvery { this@mockk.invoke(any()) } answers { teamLogoId }
        }
        val result = TeamUseCase.GetTeamDetail(
            repository = teamRepository,
            getTeamLogo = getTeamLogo,
        )(input = 1)
        if (result is Result.Success<Team, AppError>) {
            result.data.teamLogoResId shouldBe teamLogoId
        } else {
            fail("Result should be success.")
        }
    }

    @Test
    fun `should get team logo resource id`() = runTest {
        val teamLogoResId = 420
        val teamLogoRepository = mockk<TeamLogoRepository> {
            coEvery { getTeamLogoResId(teamName = any()) } returns teamLogoResId
        }
        TeamUseCase.GetTeamLogo(repository = teamLogoRepository)(input = "test") shouldBe teamLogoResId
    }

    @Test
    fun `should return null when team logo is not found`() = runTest {
        val teamLogoRepository = mockk<TeamLogoRepository> {
            coEvery { getTeamLogoResId(teamName = any()) } returns null
        }
        TeamUseCase.GetTeamLogo(repository = teamLogoRepository)(input = "test") shouldBe null
    }
}
