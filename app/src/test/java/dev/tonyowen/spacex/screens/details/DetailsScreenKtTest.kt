package dev.tonyowen.spacex.screens.details

import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.navigation.NavHostController
import androidx.test.ext.junit.runners.AndroidJUnit4
import dev.tonyowen.spacex.TestApplication
import dev.tonyowen.spacex.modules.business.rocket.Rocket
import dev.tonyowen.spacex.network.utils.NetworkResponse
import dev.tonyowen.spacex.ui.theme.SpacexTheme
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.flow.MutableStateFlow
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.annotation.Config

@RunWith(AndroidJUnit4::class)
@Config(application = TestApplication::class, instrumentedPackages = ["androidx.loader.content"])
class DetailsScreenKtTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    private val mockViewModel = mockk<DetailsScreenViewModel>()
    private val mockNavController = mockk<NavHostController>()

    @Test
    fun loadingState_showsLoadingSpinner() {
        every { mockViewModel.getRocket("1") } returns Unit
        every { mockViewModel.rocketState } returns MutableStateFlow(NetworkResponse.Loading())

        composeTestRule.setContent {
            SpacexTheme {
                DetailsScreen(viewModel = mockViewModel, rocketId = "1", navController = mockNavController)
            }
        }

        composeTestRule.onNodeWithTag(DetailsScreenTags.LOADING).assertExists()
    }

    @Test
    fun errorState_showsErrorMessage() {
        every { mockViewModel.getRocket("1") } returns Unit
        every { mockViewModel.rocketState } returns MutableStateFlow(NetworkResponse.Failure(code = 404, error = "Not Found"))

        composeTestRule.setContent {
            SpacexTheme {
                DetailsScreen(viewModel = mockViewModel, rocketId = "1", navController = mockNavController)
            }
        }

        composeTestRule.onNodeWithTag(DetailsScreenTags.ERROR).assertExists()
    }

    @Test
    fun readyState_showsContent() {
        val rocket = Rocket(
            name = "Rocket One"
        )
        every { mockViewModel.getRocket("1") } returns Unit
        every { mockViewModel.rocketState } returns MutableStateFlow(NetworkResponse.Success(rocket))

        composeTestRule.setContent {
            SpacexTheme {
                DetailsScreen(viewModel = mockViewModel, rocketId = "1", navController = mockNavController)
            }
        }

        composeTestRule.onNodeWithTag(DetailsScreenTags.CONTENT).assertExists()
        composeTestRule.onNodeWithText("Rocket One").assertExists()
    }
}