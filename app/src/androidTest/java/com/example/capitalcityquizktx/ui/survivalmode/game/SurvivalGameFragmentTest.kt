package com.example.capitalcityquizktx.ui.survivalmode.game

import android.os.Bundle
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.rule.ActivityTestRule
import com.example.capitalcityquizktx.SingleActivity
import com.example.capitalcityquizktx.R
import com.example.capitalcityquizktx.data.models.config.SurvivalGameConfig
import org.junit.Rule
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations

class SurvivalGameFragmentTest {

    private val bundle = Bundle()

    @Mock
    private lateinit var survivalGameConfigMock : SurvivalGameConfig

    private lateinit var mocks: AutoCloseable

    @Rule
    @JvmField
    var activityRule: ActivityTestRule<SingleActivity> = ActivityTestRule(SingleActivity::class.java)

    @BeforeEach
    fun setUp(){
        mocks = MockitoAnnotations.openMocks(this)
        // added to bundle
        bundle.putParcelable("survivalGameConfig", survivalGameConfigMock)
        // launch fragment with args
        launchFragmentInContainer<SurvivalGameFragment>(fragmentArgs = bundle)
    }

    @AfterEach
    fun tearDown() {
        mocks.close()
    }

    @Test
    fun checkTimerTextView_isDisplayed(){
        onView(withId(R.id.timerTextView)).check(matches(isDisplayed()))
    }

    @Test
    fun checkDivider_isDisplayed(){
        onView(withId(R.id.survival_game_divider)).check(matches(isDisplayed()))
    }

    @Test
    fun checkCounterTextView_isDisplayed(){
        onView(withId(R.id.counterTextView)).check(matches(isDisplayed()))
    }

    @Test
    fun checkCountryTextView_isDisplayed(){
        onView(withId(R.id.countryTextView)).check(matches(isDisplayed()))
    }

    @Test
    fun checkCapitalEditText_isDisplayed(){
        onView(withId(R.id.capitalEditText)).check(matches(isDisplayed()))
    }

    @Test
    fun checkEnterBtn_isDisplayed(){
        onView(withId(R.id.enterBtn)).check(matches(isDisplayed()))
    }
}