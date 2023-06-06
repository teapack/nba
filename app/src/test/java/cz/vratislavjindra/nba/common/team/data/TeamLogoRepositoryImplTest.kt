package cz.vratislavjindra.nba.common.team.data

import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNotBe
import kotlinx.coroutines.test.runTest
import org.junit.Test

internal class TeamLogoRepositoryImplTest {

    @Test
    fun `should return valid id when asking for existing team logo`() = runTest {
        val teamLogoRepository = TeamLogoRepositoryImpl()
        teamLogoRepository.getTeamLogoResId(teamName = "knicks") shouldNotBe null
    }

    @Test
    fun `should return null id when asking for non existent team logo`() = runTest {
        val teamLogoRepository = TeamLogoRepositoryImpl()
        teamLogoRepository.getTeamLogoResId(teamName = "test") shouldBe null
    }
}
