package com.example.capitalcityquizktx.domain.viewmodels

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.capitalcityquizktx.domain.GameUseCases
import com.example.capitalcityquizktx.testUtil.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.TestCoroutineScope
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert
import org.junit.Rule
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.kotlin.any

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
@ExtendWith(InstantExecutorExtension::class)
class SurvivalViewModelTest : CoroutineTest {

    // region constants

    // endregion constants

    // region helper fields
    override lateinit var testScope: TestCoroutineScope
    override lateinit var testDispatcher: TestCoroutineDispatcher

    @Mock
    lateinit var useCases : GameUseCases

    // endregion helper fields
    private lateinit var SUT : SurvivalViewModel

    private lateinit var mocks: AutoCloseable

    @BeforeEach
    fun setUp() {
        mocks = MockitoAnnotations.openMocks(this)
        SUT = SurvivalViewModel(useCases, testDispatcher)
    }

    @AfterEach
    fun tearDown() {
        mocks.close()
    }

    @Test
    fun getCountriesFrom_successfullyPostCountriesToLiveData() = testDispatcher.runBlockingTest {
        Mockito.`when`(useCases.getCountriesIn(any())).thenReturn(TestData.COUNTRIES)
        SUT.getCountriesFrom(TestData.CONTINENTS)
        Assert.assertEquals(SUT.countries.value, TestData.COUNTRIES)
    }

    // region helper methods

    // endregion helper methods

    // region helper classes

    // endregion helper class
}