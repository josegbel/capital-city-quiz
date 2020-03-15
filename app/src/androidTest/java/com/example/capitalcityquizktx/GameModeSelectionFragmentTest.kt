package com.example.capitalcityquizktx

import android.view.View
import androidx.fragment.app.testing.launchFragment
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.test.espresso.Espresso
import androidx.test.espresso.Espresso.*
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import androidx.test.rule.ActivityTestRule
import com.example.capitalcityquizktx.ui.GameModeSelectionFragment
import io.mockk.MockKAnnotations
import io.mockk.impl.annotations.MockK
import io.mockk.impl.annotations.RelaxedMockK
import io.mockk.impl.annotations.SpyK
import io.mockk.junit5.MockKExtension
import io.mockk.verify
import org.hamcrest.Matcher
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito.mock
import org.mockito.Mockito.verify

@RunWith(AndroidJUnit4::class)
@LargeTest
class GameModeSelectionFragmentTest {

//    @SpyK
//    lateinit var mockNavController : NavController

    @Rule
    @JvmField var activityRule: ActivityTestRule<MainActivity> = ActivityTestRule(MainActivity::class.java)

//    @Before
//    fun setUp() = MockKAnnotations.init(this)

    @Test
    fun when_survival_mode_button_clicked_navigates_to_game_config_fragment(){
        val scenario = launchFragmentInContainer<GameModeSelectionFragment>()
        val mockNavController = mock(NavController::class.java)

        scenario.onFragment {
            Navigation.setViewNavController(it.requireView(), mockNavController)
        }

        onView(withId(R.id.GMSFsurvivalModeBtn)).perform(click())

//        verify { mockNavController.navigate(R.id.action_gameModeSelectionFragment_to_gameConfigSurvivalFragment) }
        verify(mockNavController).navigate(R.id.action_gameModeSelectionFragment_to_gameConfigSurvivalFragment)
    }
}