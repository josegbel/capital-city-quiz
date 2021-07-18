package com.example.capitalcityquizktx

import androidx.lifecycle.MutableLiveData
import com.example.capitalcityquizktx.data.models.geographical.Continent
import com.example.capitalcityquizktx.domain.GameConfigSurvivalPresenter
import com.example.capitalcityquizktx.testUtil.InstantExecutorExtension
import com.example.capitalcityquizktx.ui.survivalmode.config.GameConfigSurvivalView
import org.junit.Assert.assertEquals
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.Mockito.verify
import org.mockito.MockitoAnnotations
import org.mockito.internal.verification.Times
import kotlin.time.ExperimentalTime

/*

    J. Garcia CapitalCityQuiz in Kotlin 2019

 */
@ExtendWith(InstantExecutorExtension::class)
class GameConfigSurvivalModePresenterTest {

    private lateinit var presenter: GameConfigSurvivalPresenter
    private val listLiveData = MutableLiveData<List<Continent>>()

    @Mock
    lateinit var view: GameConfigSurvivalView

    private lateinit var mocks: AutoCloseable

    @BeforeEach
    fun setup() {
        mocks = MockitoAnnotations.openMocks(this)
        presenter = GameConfigSurvivalPresenter(view)
    }

    @AfterEach
    fun tearDown() {
        mocks.close()
    }

    @Test
    fun `Should display number of questions selection when continents are selected`() {
        listLiveData.postValue(
            listOf(
                Continent(
                    "continentName",
                    10
                ),
                Continent(
                    "continentName",
                    10
                ),
                Continent(
                    "continentName",
                    10
                )
            )
        )
        `when`(view.continentsList).thenReturn(listLiveData)
        presenter.receiveContinentSelection()
        verify(view, Times(1)).showQuestionsNumberSelection()
    }

    @Test
    fun `Should display time set selection when continents are selected`() {
        listLiveData.postValue(
            listOf(
                Continent(
                    "continentName",
                    10
                ),
                Continent(
                    "continentName",
                    10
                ),
                Continent(
                    "continentName",
                    10
                )
            )
        )
        `when`(view.continentsList).thenReturn(listLiveData)

        presenter.receiveContinentSelection()

        verify(view, Times(1)).showQuestionsNumberSelection()
    }

    @Test
    fun `Should hide number of questions selection when no continents are selected`() {
        listLiveData.postValue(emptyList())
        `when`(view.continentsList).thenReturn(listLiveData)
        presenter.receiveContinentSelection()
        verify(view, Times(1)).hideQuestionsNumberSelection()
    }

    @Test
    fun `Should hide time set selection when no continents are selected`() {
        listLiveData.postValue(emptyList())
        `when`(view.continentsList).thenReturn(listLiveData)
        presenter.receiveContinentSelection()
        verify(view, Times(1)).hideTimeLimitSelection()
    }

    @Test
    fun `Should calculate recommended questions time limit given a number of countries part 1`() {
        val countries = 10  // 15 sec per country, expected 150
        val actual = presenter.calculateRecommendedTimeLimit(countries)
        assertEquals(150, actual)
    }

    @Test
    fun `Should calculate recommended questions time limit given a number of countries part 2`() {
        val countries = 20  // 15 sec per country, expected 150
        val actual = presenter.calculateRecommendedTimeLimit(countries)
        assertEquals(300, actual)
    }

    @Test
    fun `Should throw exception when calculating recommended questions time limit given 0 as number of countries`() {
        val countries = 0
        assertThrows<IllegalArgumentException> {
            presenter.calculateRecommendedTimeLimit(countries)
        }
    }

    @Test
    fun `Should throw exception when calculating recommended questions time limit given a negative number of countries`() {
        val countries = -5
        assertThrows<IllegalArgumentException> {
            presenter.calculateRecommendedTimeLimit(countries)
        }
    }

    @ExperimentalTime
    @Test
    fun `given less than ten seconds in millis should return a formatted string mm(colon)ss`() {
        val millis = 3000
        val actual = presenter.formatTime(millis.toLong())
        assertEquals("00:03", actual)
    }

    @ExperimentalTime
    @Test
    fun `given more than ten seconds in millis should return a formatted string mm(colon)ss`() {
        val millis = 30000
        val actual = presenter.formatTime(millis.toLong())
        assertEquals("00:30", actual)
    }

    @ExperimentalTime
    @Test
    fun `given 60 seconds in millis should return a formatted string mm(colon)ss`() {
        val millis = 60000
        val actual = presenter.formatTime(millis.toLong())
        assertEquals("01:00", actual)
    }

    @ExperimentalTime
    @Test
    fun `given more than 60 seconds in millis should return a formatted string mm(colon)ss`() {
        val millis = 75000
        val actual = presenter.formatTime(millis.toLong())
        assertEquals("01:15", actual)
    }

    @ExperimentalTime
    @Test
    fun `given 10 minutes in millis should return a formatted string mm(colon)ss`() {
        val millis = 600000
        val actual = presenter.formatTime(millis.toLong())
        assertEquals("10:00", actual)
    }

    @ExperimentalTime
    @Test
    fun `given more than 10 minutes in millis should return a formatted string mm(colon)ss`() {
        val millis = 670000
        val actual = presenter.formatTime(millis.toLong())
        assertEquals("11:10", actual)
    }

    @ExperimentalTime
    @Test
    fun `given 60 minutes in millis should return a formatted string hh(colon)mm(colon)ss`() {
        val millis = 3600000
        val actual = presenter.formatTime(millis.toLong())
        assertEquals("01:00:00", actual)
    }

    @ExperimentalTime
    @Test
    fun `given more than 1 hour in millis should return a formatted string hh(colon)mm(colon)ss`() {
        val millis = 7260000
        val actual = presenter.formatTime(millis.toLong())
        assertEquals("02:01:00", actual)
    }

    @ExperimentalTime
    @Test
    fun `given more than 10 hour in millis should return a formatted string hh(colon)mm(colon)ss`() {
        val millis = 72062000
        val actual = presenter.formatTime(millis.toLong())
        assertEquals("20:01:02", actual)
    }

    @ExperimentalTime
    @Test
    fun `given zero millis should return a formatted string hh(colon)mm(colon)ss`() {
        val millis = 0
        val actual = presenter.formatTime(millis.toLong())
        assertEquals("00:00", actual)
    }

    @ExperimentalTime
    @Test
    fun `given negative amount of millis should throw IllegalArgumentException`() {
        val millis = -5
        assertThrows<IllegalArgumentException> {
            presenter.formatTime(millis.toLong())
        }
    }
}
