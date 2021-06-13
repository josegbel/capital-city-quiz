package com.example.capitalcityquizktx.domain.viewmodels

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.capitalcityquizktx.domain.GameUseCases
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.*
import org.junit.runner.RunWith
import org.mockito.ArgumentMatchers.any
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner
import testUtil.CoroutineTestRule
import testUtil.TestData

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

    private lateinit var mocks: AutoCloseable

    @Before
    fun setUp() {
        mocks = MockitoAnnotations.openMocks(this)
        SUT = SurvivalViewModel(useCases, testCoroutineTestRule.testDispatcherProvider.io())
    }

    @After
    fun tearDown() {
        mocks.close()
    }

    @Test
    fun getCountriesFrom_successfullyPostCountriesToLiveData()
            = testCoroutineTestRule.testDispatcher.runBlockingTest {
        Mockito.`when`(useCases.getCountriesIn(any())).thenReturn(TestData.COUNTRIES)
        SUT.getCountriesFrom(TestData.CONTINENTS)
        Assert.assertEquals(SUT.countries.value, TestData.COUNTRIES)
    }

    // region helper methods

    // endregion helper methods

    // region helper classes

    // endregion helper class
}