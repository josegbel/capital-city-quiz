package com.example.capitalcityquizktx

import com.example.capitalcityquizktx.domain.GameInteractorTest
import org.junit.runner.RunWith
import org.junit.runners.Suite

@RunWith(Suite::class)
@Suite.SuiteClasses(GameInteractorTest::class,
                    ContinentSelectorTest::class,
                    DatabaseUtilsTest::class,
                    GameConfigSurvivalModePresenterTest::class,
                    SurvivalViewModelTest::class)
class UnitTestSuite