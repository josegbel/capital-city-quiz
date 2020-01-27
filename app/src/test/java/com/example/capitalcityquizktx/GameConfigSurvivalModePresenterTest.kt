package com.example.capitalcityquizktx

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import com.example.capitalcityquizktx.domain.GameConfigSurvivalPresenter
import com.example.capitalcityquizktx.model.database.Continent
import com.example.capitalcityquizktx.ui.survivalmode.config.GameConfigSurvivalView
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.RelaxedMockK
import io.mockk.verify
import org.junit.Before
import org.junit.Rule
import org.junit.Test

/*

    J. Garcia CapitalCityQuiz in Kotlin 2019

 */
class GameConfigSurvivalModePresenterTest{

    private lateinit var presenter : GameConfigSurvivalPresenter
    private val listLiveData = MutableLiveData<List<Continent>>()

    @get:Rule
    val rule = InstantTaskExecutorRule()

    @RelaxedMockK
    lateinit var view: GameConfigSurvivalView

    @Before
    fun setup() {
        MockKAnnotations.init(this)
        presenter = GameConfigSurvivalPresenter(view)
    }

    @Test fun `Should display number of questions selection when continents are selected`(){
        listLiveData.postValue(listOf(
            Continent("continentName", 10),
            Continent("continentName", 10),
            Continent("continentName", 10)))
        every { view.continentsList } returns listLiveData

        presenter.receiveContinentSelection()

        verify { view.showQuestionsNumberSelection() }
    }

    @Test fun `Should display time set selection when continents are selected`(){
        listLiveData.postValue(listOf(
            Continent("continentName", 10),
            Continent("continentName", 10),
            Continent("continentName", 10)))
        every { view.continentsList } returns listLiveData

        presenter.receiveContinentSelection()

        verify { view.showTimeLimitSelection() }
    }

    @Test fun `Should hide number of questions selection when no continents are selected`(){
        listLiveData.postValue(emptyList())
        every { view.continentsList } returns listLiveData

        presenter.receiveContinentSelection()

        verify { view.hideQuestionsNumberSelection()}
    }

    @Test fun `Should hide time set selection when no continents are selected`(){
        listLiveData.postValue(emptyList())
        every { view.continentsList } returns listLiveData

        presenter.receiveContinentSelection()

        verify { view.hideTimeLimitSelection()}
    }
}
