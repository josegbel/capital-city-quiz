package com.example.capitalcityquizktx

import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import com.example.capitalcityquizktx.ui.register.RegisterFragment
import org.junit.Rule
import org.junit.jupiter.api.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class RegisterFragmentTest {

    @Rule
    @JvmField
    var activityRule: ActivityTestRule<MainActivity> = ActivityTestRule(MainActivity::class.java)

    @Test
    fun testingVisibilityOfAllViewsInLayout() {
        val scenario = launchFragmentInContainer<RegisterFragment>()

        onView(withId(R.id.usernameRegET)).check(matches(isDisplayed()))

        onView(withId(R.id.passwordRegET)).check(matches(isDisplayed()))

        onView(withId(R.id.firstNameRegET)).check(matches(isDisplayed()))

        onView(withId(R.id.lastNameRegET)).check(matches(isDisplayed()))

        onView(withId(R.id.emailRegET)).check(matches(isDisplayed()))
    }
}
