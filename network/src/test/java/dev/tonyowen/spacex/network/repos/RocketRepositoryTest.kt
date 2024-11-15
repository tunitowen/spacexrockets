package dev.tonyowen.spacex.network.repos

import dev.tonyowen.spacex.modules.business.rocket.Rocket
import dev.tonyowen.spacex.modules.network.rocket.RocketDto
import dev.tonyowen.spacex.network.services.RocketService
import dev.tonyowen.spacex.network.utils.NetworkResponse
import io.mockk.coEvery
import io.mockk.mockk
import junit.framework.TestCase.assertTrue
import kotlinx.coroutines.test.runTest
import okhttp3.ResponseBody.Companion.toResponseBody
import org.junit.Before
import org.junit.Test
import retrofit2.Response

class RocketRepositoryTest {

    private lateinit var repo: RocketRepository
    private val mockRocketService = mockk<RocketService>()

    @Before
    fun setup() {
        repo = RocketRepository(mockRocketService)
    }

    @Test
    fun listOfRockets_successShouldReturnSuccessObject() = runTest {
        coEvery { mockRocketService.getRockets() } returns Response.success(emptyList())
        val result = repo.getRockets()
        assertTrue(result is NetworkResponse.Success)
    }

    @Test
    fun listOfRockets_failureShouldReturnFailureObject() = runTest {
        coEvery { mockRocketService.getRockets() } returns Response.error(404, "".toResponseBody())
        val result = repo.getRockets()
        assertTrue(result is NetworkResponse.Failure)
    }

    @Test
    fun rocketById_successShouldReturnSuccessObject() = runTest {
        coEvery { mockRocketService.getRocket("1") } returns Response.success(RocketDto())
        val result = repo.getRocketById("1")
        assertTrue(result is NetworkResponse.Success)
    }

    @Test
    fun rocketById_failureShouldReturnFailureObject() = runTest {
        coEvery { mockRocketService.getRocket("1") } returns Response.error(404, "".toResponseBody())
        val result = repo.getRocketById("1")
        assertTrue(result is NetworkResponse.Failure)
    }

    @Test
    fun rocketsSuccess_shouldMapToRocket() = runTest {
        coEvery { mockRocketService.getRockets() } returns Response.success(listOf(RocketDto(id = "123")))
        val result = repo.getRockets()
        assertTrue(result is NetworkResponse.Success)
        assertTrue(result.data!![0].id == "123")
    }

    @Test
    fun rocketByIdSuccess_shouldMapToRocket() = runTest {
        coEvery { mockRocketService.getRocket("123") } returns Response.success(RocketDto(id = "123"))
        val result = repo.getRocketById("123")
        assertTrue(result is NetworkResponse.Success)
        assertTrue(result.data!!.id == "123")
    }
}