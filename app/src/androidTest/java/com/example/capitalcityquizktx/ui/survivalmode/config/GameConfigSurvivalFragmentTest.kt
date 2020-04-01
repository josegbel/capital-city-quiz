package com.example.capitalcityquizktx.ui.survivalmode.config

import androidTestUtils.SeekbarActions
import androidTestUtils.TextViewActions
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.ViewInteraction
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import com.example.capitalcityquizktx.MainActivity
import com.example.capitalcityquizktx.R
import com.example.capitalcityquizktx.model.database.continents.Africa
import com.example.capitalcityquizktx.model.database.continents.Asia
import com.example.capitalcityquizktx.model.database.continents.Europe
import org.junit.Assert.assertEquals
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
    fun setUp(){
        launchFragmentInContainer<GameConfigSurvivalFragment>()
    }

    @Test
    fun checkEuropeChip_IsDisplayed(){
        onView(withId(R.id.europeSurvChip)).check(matches(isDisplayed()))
    }

    @Test
    fun checkSouthAmericaChip_IsDisplayed(){
        onView(withId(R.id.southAmericaSurvChip)).check(matches(isDisplayed()))
    }

    @Test
    fun checkNorthAmericaChip_IsDisplayed(){
        onView(withId(R.id.northAmericaSurvChip)).check(matches(isDisplayed()))
    }

    @Test
    fun checkAustraliaChip_IsDisplayed(){
        onView(withId(R.id.australiaSurvChip)).check(matches(isDisplayed()))
    }

    @Test
    fun checkAfricaChip_IsDisplayed(){
        onView(withId(R.id.africaSurvChip)).check(matches(isDisplayed()))
    }

    @Test
    fun checkAsiaChip_IsDisplayed(){
        onView(withId(R.id.asiaSurvChip)).check(matches(isDisplayed()))
    }

    @Test
    fun checkAllContinentsChipChip_IsDisplayed(){
        onView(withId(R.id.allContinentsSurvChip)).check(matches(isDisplayed()))
    }

    @Test
    fun checkStartBtn_IsDisplayed(){
        onView(withId(R.id.gameConfigSurvPlayBtn)).check(matches(isDisplayed()))
    }

    @Test
    fun whenEuropeChipIsSelected_DisplaySeekBarsAndTextViews_numberOfCountriesAndTimeLimit() {
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
        onView(withId(R.id.europeSurvChip)).perform(click())

        onView(withId(R.id.europeSurvChip)).check(matches(isChecked()))
    }

    @Test
    fun whenAfricaChipIsClicked_CheckIsSelected() {
        onView(withId(R.id.africaSurvChip)).perform(click())

        onView(withId(R.id.africaSurvChip)).check(matches(isChecked()))
    }

    @Test
    fun whenSouthAmericaChipIsClicked_CheckIsSelected() {
        onView(withId(R.id.southAmericaSurvChip)).perform(click())

        onView(withId(R.id.southAmericaSurvChip)).check(matches(isChecked()))
    }

    @Test
    fun whenNorthAmericaChipIsClicked_CheckIsSelected() {
        onView(withId(R.id.northAmericaSurvChip)).perform(click())

        onView(withId(R.id.northAmericaSurvChip)).check(matches(isChecked()))
    }

    @Test
    fun whenAsiaChipIsClicked_CheckIsSelected() {
        onView(withId(R.id.asiaSurvChip)).perform(click())

        onView(withId(R.id.asiaSurvChip)).check(matches(isChecked()))
    }

    @Test
    fun whenAustraliaChipIsClicked_CheckIsSelected() {
        onView(withId(R.id.australiaSurvChip)).perform(click())

        onView(withId(R.id.australiaSurvChip)).check(matches(isChecked()))
    }

    @Test
    fun checkingAfricaChipShouldDisplayMaximumTimeLimitOnTimeLimitSeekbar(){
        onView(withId(R.id.africaSurvChip)).perform(click())

        val timeLimitTextViewVI : ViewInteraction = onView(withId(R.id.timeLimitTv))

        val timeLimitTextView = TextViewActions.getText(timeLimitTextViewVI)

        assertEquals("${Africa.totalCountries * 30} seconds", timeLimitTextView)
    }

    /**
     * ViewInteraction helps to retrieve the state of the View.
     * SeekbarActions class is used to retrieve those fields in the ViewInteraction
     */
    @Test
    fun checkingAfricaChipShouldAndSettingTimeLimitSeekBarToMaxShouldDisplayMaxTimeLimitOnTimeLimitTV(){
        onView(withId(R.id.africaSurvChip)).perform(click())

        val timeLimitSeekBarVI : ViewInteraction = onView(withId(R.id.timeLimitSeekBar))

        onView(withId(R.id.countriesNumberSeekBar)).perform(SeekbarActions.setProgress(Africa.totalCountries))

        val max = SeekbarActions.getMax(timeLimitSeekBarVI)

        onView(withId(R.id.timeLimitSeekBar)).perform(SeekbarActions.setProgress(max))

        val timeLimitTextViewVI : ViewInteraction = onView(withId(R.id.timeLimitTv))

        val timeLimitTextView = TextViewActions.getText(timeLimitTextViewVI)

        assertEquals("${Africa.totalCountries * 30} seconds", timeLimitTextView)
    }

    @Test
    fun checkingAChipAndSettingSeekBarProgressShouldDisplayCorrectInfoOnTimeLimitSeekBarTV(){
        val selectedCountries = 10

        onView(withId(R.id.africaSurvChip)).perform(click())

        onView(withId(R.id.countriesNumberSeekBar))
            .perform(SeekbarActions.setProgress(selectedCountries))

        val timeLimitTextViewVI : ViewInteraction = onView(withId(R.id.timeLimitTv))

        val timeLimitTextView = TextViewActions.getText(timeLimitTextViewVI)

        assertEquals("300 seconds", timeLimitTextView)
    }

    //TODO Create a larger test that tests the seekbar with swipe left to right and checks the different states of the text views
    @Test
    fun selectingACoupleOfChipsShouldCheckMinAndMaxValuesOfSeekBarsTimeLimitText(){
        onView(withId(R.id.europeSurvChip)).perform(click())

        onView(withId(R.id.asiaSurvChip)).perform(click())

        onView(withId(R.id.countriesNumberSeekBar)).perform(SeekbarActions.setProgress(1))

        val timeLimitTextViewVI : ViewInteraction = onView(withId(R.id.timeLimitTv))

        var timeLimitTextView = TextViewActions.getText(timeLimitTextViewVI)

        assertEquals("30 seconds", timeLimitTextView)

        val countriesNumberSeekBarVI : ViewInteraction = onView(withId(R.id.countriesNumberSeekBar))

        val maxCountries = SeekbarActions.getMax(countriesNumberSeekBarVI)

        onView(withId(R.id.countriesNumberSeekBar)).perform(SeekbarActions.setProgress(maxCountries))

        val timeLimitSeekBarVI : ViewInteraction = onView(withId(R.id.timeLimitSeekBar))

        val maxTimeLimit = SeekbarActions.getMax(timeLimitSeekBarVI)

        onView(withId(R.id.timeLimitSeekBar)).perform(SeekbarActions.setProgress(maxTimeLimit))

        timeLimitTextView = TextViewActions.getText(timeLimitTextViewVI)

        assertEquals(
            "${(Europe.totalCountries + Asia.totalCountries) * 30} seconds", timeLimitTextView)
    }
}