package dev.tonyowen.spacex.screens.home

import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onAllNodesWithTag
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import androidx.navigation.NavHostController
import androidx.test.ext.junit.runners.AndroidJUnit4
import dev.tonyowen.spacex.TestApplication
import dev.tonyowen.spacex.modules.business.rocket.Rocket
import dev.tonyowen.spacex.navigation.DetailsDestination
import dev.tonyowen.spacex.network.utils.NetworkResponse
import dev.tonyowen.spacex.ui.theme.SpacexTheme
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.flow.MutableStateFlow
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.annotation.Config

@RunWith(AndroidJUnit4::class)
@Config(application = TestApplication::class, instrumentedPackages = ["androidx.loader.content"])
class HomeScreenKtTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    private val mockViewModel = mockk<HomeScreenViewModel>()
    private val mockNavController = mockk<NavHostController>()

    @Test
    fun loadingState_showsLoadingSpinner() {
        every { mockViewModel.getRockets() } returns Unit
        every { mockViewModel.rocketsState } returns MutableStateFlow(NetworkResponse.Loading())

        composeTestRule.setContent {
            SpacexTheme {
                HomeScreen(viewModel = mockViewModel, navController = mockNavController)
            }
        }

        composeTestRule.onNodeWithTag(HomeScreenTags.LOADING).assertExists()
    }

    @Test
    fun errorState_showsErrorMessage() {
        every { mockViewModel.getRockets() } returns Unit
        every { mockViewModel.rocketsState } returns MutableStateFlow(NetworkResponse.Failure(code = 404, error = "Not Found"))

        composeTestRule.setContent {
            SpacexTheme {
                HomeScreen(viewModel = mockViewModel, navController = mockNavController)
            }
        }

        composeTestRule.onNodeWithTag(HomeScreenTags.ERROR).assertExists()
    }

    @Test
    fun readyState_showsContent() {
        every { mockViewModel.getRockets() } returns Unit
        every { mockViewModel.rocketsState } returns MutableStateFlow(NetworkResponse.Success(emptyList()))

        composeTestRule.setContent {
            SpacexTheme {
                HomeScreen(viewModel = mockViewModel, navController = mockNavController)
            }
        }

        composeTestRule.onNodeWithTag(HomeScreenTags.CONTENT).assertExists()
    }

    @Test
    fun readyState_showsContent_withListItems() {
        val mockRocket1 = mockk<Rocket>()
        every { mockRocket1.images } returns listOf("url1")
        every { mockRocket1.name } returns "test1"

        val mockRocket2 = mockk<Rocket>()
        every { mockRocket2.images } returns listOf("url2")
        every { mockRocket2.name } returns "test2"

        every { mockViewModel.getRockets() } returns Unit
        every { mockViewModel.rocketsState } returns MutableStateFlow(
            NetworkResponse.Success(
                listOf(
                    mockRocket1, mockRocket2
                )
            )
        )

        composeTestRule.setContent {
            SpacexTheme {
                HomeScreen(viewModel = mockViewModel, navController = mockNavController)
            }
        }

        val numberOfCards = composeTestRule.onAllNodesWithTag(HomeScreenTags.CARD).fetchSemanticsNodes().size
        assertEquals(numberOfCards, 2)
    }

    @Test
    fun pressingCard_callsNavController() {
        val mockRocket1 = mockk<Rocket>()
        every { mockRocket1.id } returns "1"
        every { mockRocket1.images } returns listOf("url1")
        every { mockRocket1.name } returns "test1"

        every { mockViewModel.getRockets() } returns Unit
        every { mockViewModel.rocketsState } returns MutableStateFlow(
            NetworkResponse.Success(
                listOf(
                    mockRocket1
                )
            )
        )

        every { mockNavController.navigate(DetailsDestination("1"))} returns Unit

        composeTestRule.setContent {
            SpacexTheme {
                HomeScreen(viewModel = mockViewModel, navController = mockNavController)
            }
        }

        composeTestRule.onNodeWithTag(HomeScreenTags.CARD).performClick()
        verify { mockNavController.navigate(DetailsDestination("1")) }
    }

}