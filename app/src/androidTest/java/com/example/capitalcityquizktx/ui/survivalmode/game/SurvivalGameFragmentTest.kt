package com.example.capitalcityquizktx.ui.survivalmode.game

import android.os.Bundle
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import com.example.capitalcityquizktx.MainActivity
import com.example.capitalcityquizktx.R
import com.example.capitalcityquizktx.data.models.config.SurvivalGameConfig
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito

@RunWith(AndroidJUnit4::class)
class SurvivalGameFragmentTest {

    private val bundle = Bundle()

    @Rule
    @JvmField
    var activityRule: ActivityTestRule<MainActivity> = ActivityTestRule(MainActivity::class.java)

    @Before
    fun setUp(){
        // fragment argument mock
        val survivalGameConfigMock = Mockito.mock(SurvivalGameConfig::class.java)
        // added to bundle
        bundle.putParcelable("survivalGameConfig", survivalGameConfigMock)
        // launch fragment with args
        launchFragmentInContainer<SurvivalGameFragment>(fragmentArgs = bundle)
    }

    @Test
    fun checkTimerTextView_isDisplayed(){
        onView(withId(R.id.timerTextView)).check(matches(ViewMatchers.isDisplayed()))
    }

    @Test
    fun checkDivider_isDisplayed(){
        onView(withId(R.id.survival_game_divider)).check(matches(ViewMatchers.isDisplayed()))
    }

    @Test
    fun checkCounterTextView_isDisplayed(){
        onView(withId(R.id.counterTextView)).check(matches(ViewMatchers.isDisplayed()))
    }

    @Test
    fun checkCountryTextView_isDisplayed(){
        onView(withId(R.id.countryTextView)).check(matches(ViewMatchers.isDisplayed()))
    }

    @Test
    fun checkCapitalEditText_isDisplayed(){
        onView(withId(R.id.capitalEditText)).check(matches(ViewMatchers.isDisplayed()))
    }

    @Test
    fun checkEnterBtn_isDisplayed(){
        onView(withId(R.id.enterBtn)).check(matches(ViewMatchers.isDisplayed()))
    }
}