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
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class GameConfigSurvivalFragmentTest {

    @Rule
    @JvmField
    val activityRule: ActivityTestRule<MainActivity> = ActivityTestRule(MainActivity::class.java)

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

        onView(withId(R.id.countriesNumberSeekBar)).check(matches(isDisplayed()))
        onView(withId(R.id.selectCountriesNumberTv)).check(matches(isDisplayed()))
        onView(withId(R.id.selectedCountriesTV)).check(matches(isDisplayed()))
        onView(withId(R.id.timeLimitSeekBar)).check(matches(isDisplayed()))
        onView(withId(R.id.timeLimitTv)).check(matches(isDisplayed()))
        onView(withId(R.id.selectTimeLimitTv)).check(matches(isDisplayed()))
    }

    @Test
    fun whenAfricaChipIsSelected_DisplaySeekBarsAndTextViews_numberOfCountriesAndTimeLimit() {
        launchFragmentInContainer<GameConfigSurvivalFragment>()

        onView(withId(R.id.africaSurvChip)).perform(click())

        onView(withId(R.id.countriesNumberSeekBar)).check(matches(isDisplayed()))
        onView(withId(R.id.selectCountriesNumberTv)).check(matches(isDisplayed()))
        onView(withId(R.id.selectedCountriesTV)).check(matches(isDisplayed()))
        onView(withId(R.id.timeLimitSeekBar)).check(matches(isDisplayed()))
        onView(withId(R.id.timeLimitTv)).check(matches(isDisplayed()))
        onView(withId(R.id.selectTimeLimitTv)).check(matches(isDisplayed()))
    }

    @Test
    fun whenSouthAmericaChipIsSelected_DisplaySeekBarsAndTextViews_numberOfCountriesAndTimeLimit() {
        launchFragmentInContainer<GameConfigSurvivalFragment>()

        onView(withId(R.id.southAmericaSurvChip)).perform(click())

        onView(withId(R.id.countriesNumberSeekBar)).check(matches(isDisplayed()))
        onView(withId(R.id.selectCountriesNumberTv)).check(matches(isDisplayed()))
        onView(withId(R.id.selectedCountriesTV)).check(matches(isDisplayed()))
        onView(withId(R.id.timeLimitSeekBar)).check(matches(isDisplayed()))
        onView(withId(R.id.timeLimitTv)).check(matches(isDisplayed()))
        onView(withId(R.id.selectTimeLimitTv)).check(matches(isDisplayed()))
    }

    @Test
    fun whenNorthAmericaChipIsSelected_DisplaySeekBarsAndTextViews_numberOfCountriesAndTimeLimit() {
        launchFragmentInContainer<GameConfigSurvivalFragment>()

        onView(withId(R.id.northAmericaSurvChip)).perform(click())

        onView(withId(R.id.countriesNumberSeekBar)).check(matches(isDisplayed()))
        onView(withId(R.id.selectCountriesNumberTv)).check(matches(isDisplayed()))
        onView(withId(R.id.selectedCountriesTV)).check(matches(isDisplayed()))
        onView(withId(R.id.timeLimitSeekBar)).check(matches(isDisplayed()))
        onView(withId(R.id.timeLimitTv)).check(matches(isDisplayed()))
        onView(withId(R.id.selectTimeLimitTv)).check(matches(isDisplayed()))
    }

    @Test
    fun whenAsiaChipIsSelected_DisplaySeekBarsAndTextViews_numberOfCountriesAndTimeLimit() {
        launchFragmentInContainer<GameConfigSurvivalFragment>()

        onView(withId(R.id.asiaSurvChip)).perform(click())

        onView(withId(R.id.countriesNumberSeekBar)).check(matches(isDisplayed()))
        onView(withId(R.id.selectCountriesNumberTv)).check(matches(isDisplayed()))
        onView(withId(R.id.selectedCountriesTV)).check(matches(isDisplayed()))
        onView(withId(R.id.timeLimitSeekBar)).check(matches(isDisplayed()))
        onView(withId(R.id.timeLimitTv)).check(matches(isDisplayed()))
        onView(withId(R.id.selectTimeLimitTv)).check(matches(isDisplayed()))
    }

    @Test
    fun whenAustraliaChipIsSelected_DisplaySeekBarsAndTextViews_numberOfCountriesAndTimeLimit() {
        launchFragmentInContainer<GameConfigSurvivalFragment>()

        onView(withId(R.id.australiaSurvChip)).perform(click())

        onView(withId(R.id.countriesNumberSeekBar)).check(matches(isDisplayed()))
        onView(withId(R.id.selectCountriesNumberTv)).check(matches(isDisplayed()))
        onView(withId(R.id.selectedCountriesTV)).check(matches(isDisplayed()))
        onView(withId(R.id.timeLimitSeekBar)).check(matches(isDisplayed()))
        onView(withId(R.id.timeLimitTv)).check(matches(isDisplayed()))
        onView(withId(R.id.selectTimeLimitTv)).check(matches(isDisplayed()))
    }

    @Test
    fun whenEuropeChipIsClicked_CheckIsSelected() {
        launchFragmentInContainer<GameConfigSurvivalFragment>()

        onView(withId(R.id.europeSurvChip)).perform(click())

        onView(withId(R.id.europeSurvChip)).check(matches(isChecked()))
    }

    @Test
    fun whenAfricaChipIsClicked_CheckIsSelected() {
        launchFragmentInContainer<GameConfigSurvivalFragment>()

        onView(withId(R.id.africaSurvChip)).perform(click())

        onView(withId(R.id.africaSurvChip)).check(matches(isChecked()))
    }

    @Test
    fun whenSouthAmericaChipIsClicked_CheckIsSelected() {
        launchFragmentInContainer<GameConfigSurvivalFragment>()

        onView(withId(R.id.southAmericaSurvChip)).perform(click())

        onView(withId(R.id.southAmericaSurvChip)).check(matches(isChecked()))
    }

    @Test
    fun whenNorthAmericaChipIsClicked_CheckIsSelected() {
        launchFragmentInContainer<GameConfigSurvivalFragment>()

        onView(withId(R.id.northAmericaSurvChip)).perform(click())

        onView(withId(R.id.northAmericaSurvChip)).check(matches(isChecked()))
    }

    @Test
    fun whenAsiaChipIsClicked_CheckIsSelected() {
        launchFragmentInContainer<GameConfigSurvivalFragment>()

        onView(withId(R.id.asiaSurvChip)).perform(click())

        onView(withId(R.id.asiaSurvChip)).check(matches(isChecked()))
    }

    @Test
    fun whenAustraliaChipIsClicked_CheckIsSelected() {
        launchFragmentInContainer<GameConfigSurvivalFragment>()

        onView(withId(R.id.australiaSurvChip)).perform(click())

        onView(withId(R.id.australiaSurvChip)).check(matches(isChecked()))
    }
}