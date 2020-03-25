package com.example.capitalcityquizktx.ui.survivalmode.config

import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.test.espresso.Espresso
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import com.example.capitalcityquizktx.MainActivity
import com.example.capitalcityquizktx.R
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class GameConfigSurvivalFragmentTest {

    @Rule
    @JvmField
    val activityRule : ActivityTestRule<MainActivity> = ActivityTestRule(MainActivity::class.java)

    @Before
    fun setUp() {
    }

    @After
    fun tearDown() {
    }

    @Test
    fun when_press_back_navigate_to_game_selection_fragment(){
        val scenario = launchFragmentInContainer<GameConfigSurvivalFragment>()

        Espresso.pressBack()

        Espresso.onView(withId(R.id.gameModeSelectionConstraint)).check(matches(isDisplayed()))
    }
}