package dev.tonyowen.spacex.screens.details

import dev.tonyowen.spacex.modules.business.rocket.Rocket
import dev.tonyowen.spacex.network.repos.RocketRepository
import dev.tonyowen.spacex.network.utils.NetworkResponse
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.Before
import org.junit.Test

class DetailsScreenViewModelTest {

    private lateinit var viewModel: DetailsScreenViewModel
    private val mockRocketRepository = mockk<RocketRepository>()

    @OptIn(ExperimentalCoroutinesApi::class)
    @Before
    fun setup() {
        Dispatchers.setMain(UnconfinedTestDispatcher())
        viewModel = DetailsScreenViewModel(mockRocketRepository)
    }

    @Test
    fun getRocket_callsRepository() = runTest {
        coEvery { mockRocketRepository.getRocketById("1") } returns NetworkResponse.Success(Rocket(
            id = "1", name= "Falcon 9", active = true, images = emptyList(), engineType = "type"
        ))
        viewModel.getRocket("1")
        coVerify { mockRocketRepository.getRocketById("1") }
    }

    @Test
    fun getRocket_success_setsState() = runTest {
        coEvery { mockRocketRepository.getRocketById("1") } returns NetworkResponse.Success(Rocket(
            id = "1", name= "Falcon 9", active = true, images = emptyList(), engineType = "type"
        ))
        viewModel.getRocket("1")
        assert(viewModel.rocket.value.data!!.id == "1")
    }

    @Test
    fun getRocket_error_setsState() = runTest {
        coEvery { mockRocketRepository.getRocketById("1") } returns NetworkResponse.Failure(code = 404, error = "Not Found")
        viewModel.getRocket("1")
        assert(viewModel.rocket.value.error != null)
    }
}