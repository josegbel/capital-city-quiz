package com.example.capitalcityquizktx

import androidx.fragment.app.testing.launchFragment
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import androidx.test.rule.ActivityTestRule
import com.example.capitalcityquizktx.ui.LoginFragment
import com.example.capitalcityquizktx.ui.LoginFragmentDirections
import com.example.capitalcityquizktx.ui.register.RegisterFragment
import org.junit.Rule
import org.junit.jupiter.api.Test
import org.junit.runner.RunWith
import org.mockito.Mockito

class LoginFragmentTest {

    @Rule
    @JvmField
    var activityRule: ActivityTestRule<MainActivity> = ActivityTestRule(MainActivity::class.java)

    @Test
    fun whenClickedOnRegisterBtnNavigatesToRegisterFragment() {
        val scenario = launchFragmentInContainer<LoginFragment>()
        val mockNavController = Mockito.mock(NavController::class.java)
        scenario.onFragment {
            Navigation.setViewNavController(it.requireView(), mockNavController)
        }
        onView(withId(R.id.registerBtn)).perform(click())
        Mockito.verify(mockNavController).navigate(LoginFragmentDirections.actionLoginFragmentToRegisterFragment())
    }
}