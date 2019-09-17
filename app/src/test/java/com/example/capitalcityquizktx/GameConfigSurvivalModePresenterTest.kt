package com.example.capitalcityquizktx

import com.example.capitalcityquizktx.Database.Continent
import com.example.capitalcityquizktx.UI.SurvivalMode.GameConfigSurvivalModeView
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.RelaxedMockK
import io.mockk.verify
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

class GameConfigSurvivalModePresenterTest{

    private lateinit var presenter : GameConfigSurvivalModePresenter

    @RelaxedMockK
    lateinit var view: GameConfigSurvivalModeView

    @Before
    fun setup() {
        MockKAnnotations.init(this)
        presenter = GameConfigSurvivalModePresenter(view)
    }

    @Test fun `Should display number of questions selection when continents are selected`(){
        every { view.getContinentSelection() } returns listOf(
            Continent(10),
            Continent(10),
            Continent(10))

        presenter.receiveContinentSelection()

        verify { view.showQuestionsNumberSelection() }
    }

    @Test fun `Should display time set selection when continents are selected`(){
        every { view.getContinentSelection() } returns listOf(
            Continent(10),
            Continent(10),
            Continent(10))

        presenter.receiveContinentSelection()

        verify { view.showTimesetSelection() }
    }

    @Test fun `Should hide number of questions selection when no continents are selected`(){
        every { view.getContinentSelection() } returns emptyList()

        presenter.receiveContinentSelection()

        verify { view.hideQuestionsNumberSelection()}
    }

    @Test fun `Should hide time set selection when no continents are selected`(){
        every { view.getContinentSelection() } returns emptyList()

        presenter.receiveContinentSelection()

        verify { view.hideTimesetSelection()}
    }
}