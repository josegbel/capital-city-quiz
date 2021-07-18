package com.example.capitalcityquizktx

import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.rule.ActivityTestRule
import com.example.capitalcityquizktx.ui.gamemodeselection.GameModeSelectionFragment
import org.junit.Rule
import org.junit.jupiter.api.Test
import org.mockito.Mockito.mock
import org.mockito.Mockito.verify

class GameModeSelectionFragmentTest {

    @Rule
    @JvmField
    var activityRule: ActivityTestRule<SingleActivity> = ActivityTestRule(SingleActivity::class.java)

    @Test
    fun when_survival_mode_button_clicked_navigates_to_game_config_fragment(){
        val scenario = launchFragmentInContainer<GameModeSelectionFragment>()
        val mockNavController = mock(NavController::class.java)
        scenario.onFragment {
            Navigation.setViewNavController(it.requireView(), mockNavController)
        }
        onView(withId(R.id.game_mode_selection_survival_mode_btn)).perform(click())
        verify(mockNavController).navigate(R.id.action_gameModeSelectionFragment_to_gameConfigSurvivalFragment)
    }
}