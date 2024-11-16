package dev.tonyowen.spacex.screens.home

import dev.tonyowen.spacex.modules.business.rocket.Rocket
import dev.tonyowen.spacex.network.repos.RocketRepository
import dev.tonyowen.spacex.network.utils.NetworkResponse
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import junit.framework.TestCase.assertTrue
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.Before
import org.junit.Test

class HomeScreenViewModelTest {

    private lateinit var viewModel: HomeScreenViewModel
    private val mockRocketRepository = mockk<RocketRepository>()

    @OptIn(ExperimentalCoroutinesApi::class)
    @Before
    fun setUp() {
        Dispatchers.setMain(UnconfinedTestDispatcher())
        viewModel = HomeScreenViewModel(rocketRepository = mockRocketRepository)
    }

    @Test
    fun callingGetRockets_callsRepository() = runTest {
        coEvery { mockRocketRepository.getRockets() } returns NetworkResponse.Success(emptyList())
        viewModel.getRockets()
        coVerify { mockRocketRepository.getRockets() }
    }

    @Test
    fun getRocketsSuccess_setsState() = runTest {
        coEvery { mockRocketRepository.getRockets() } returns NetworkResponse.Success(
            listOf(
                Rocket(
                    id = "1",
                    name = "test",
                    engineType = "type",
                    active = true,
                    images = emptyList()
                )
            )
        )
        viewModel.getRockets()
        assertTrue(viewModel.rockets.value.data?.size == 1);
    }

    @Test
    fun getRocketsFailure_setsState() = runTest {
        coEvery { mockRocketRepository.getRockets() } returns NetworkResponse.Failure(code = 404, error = "Not Found")
        viewModel.getRockets()
        assertTrue(viewModel.rockets.value.error != null);
    }

}