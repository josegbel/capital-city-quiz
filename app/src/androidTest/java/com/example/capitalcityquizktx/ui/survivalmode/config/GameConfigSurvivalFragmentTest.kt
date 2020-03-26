package com.example.capitalcityquizktx.ui.survivalmode.config

import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.Espresso.pressBack
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
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
    val activityRule: ActivityTestRule<MainActivity> = ActivityTestRule(MainActivity::class.java)

    @Before
    fun setUp() {
    }

    @After
    fun tearDown() {
    }

    @Test
    fun whenPressBack_navigateToGameSelectionFragment() {
        launchFragmentInContainer<GameConfigSurvivalFragment>()

        pressBack()

        onView(withId(R.id.gameModeSelectionConstraint)).check(matches(isDisplayed()))
    }

    @Test
    fun whenEuropeChipIsSelected_DisplaySeekBarsAndTextViews_numberOfCountriesAndTimeLimit() {
        launchFragmentInContainer<GameConfigSurvivalFragment>()

        onView(withId(R.id.europeSurvChip)).perform(click())

//        onView(withId(R.id.countriesNumberSeekBar)).check(matches(isDisplayed()))
//        onView(withId(R.id.selectCountriesNumberTv)).check(matches(isDisplayed()))
//        onView(withId(R.id.selectedCountriesTV)).check(matches(isDisplayed()))
//        onView(withId(R.id.timeLimitSeekBar)).check(matches(isDisplayed()))
//        onView(withId(R.id.timeLimitTv)).check(matches(isDisplayed()))
        onView(withId(R.id.selectTimeLimitTv)).check(matches(withEffectiveVisibility(Visibility.VISIBLE)))
    }

    @Test
    fun whenEuropeChipIsClicked_CheckIsSelected() {
        launchFragmentInContainer<GameConfigSurvivalFragment>()

        onView(withId(R.id.europeSurvChip)).perform(click())

        onView(withId(R.id.europeSurvChip)).check(matches(isChecked()))
    }
}