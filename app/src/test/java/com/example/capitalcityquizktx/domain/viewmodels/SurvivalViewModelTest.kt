package com.example.capitalcityquizktx.domain.viewmodels

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import assertk.assertThat
import com.example.capitalcityquizktx.domain.GameUseCases
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.hamcrest.CoreMatchers
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.stubbing.Answer
import testUtil.CoroutineTestRule
import testUtil.TestData
import testUtil.getOrAwaitValue

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class SurvivalViewModelTest {

    @get: Rule
    val testCoroutineTestRule = CoroutineTestRule()

    @get: Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    // region constants

    // endregion constants

    // region helper fields
    @Mock
    lateinit var useCases : GameUseCases

    // endregion helper fields
    private lateinit var SUT : SurvivalViewModel

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        SUT = SurvivalViewModel(useCases, testCoroutineTestRule.testDispatcherProvider.io())
    }

    @Test
    fun getCountriesFrom_succesfullyPostCountriesToLiveData()
            = testCoroutineTestRule.testDispatcher.runBlockingTest {
        whenever(useCases.getCountriesIn(any())).thenReturn(TestData.COUNTRIES)
        SUT.getCountriesFrom(TestData.CONTINENTS)
        Assert.assertEquals(SUT.countries.value, TestData.COUNTRIES)
    }

    // region helper methods

    // endregion helper methods

    // region helper classes

    // endregion helper class
}