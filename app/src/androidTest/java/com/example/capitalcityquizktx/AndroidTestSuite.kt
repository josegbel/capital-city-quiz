package com.example.capitalcityquizktx

import com.example.capitalcityquizktx.ui.survivalmode.config.GameConfigSurvivalFragmentTest
import com.example.capitalcityquizktx.ui.survivalmode.game.SurvivalGameFragmentTest
import org.junit.runner.RunWith
import org.junit.runners.Suite

@RunWith(Suite::class)
@Suite.SuiteClasses(GameModeSelectionFragmentTest::class,
                    SurvivalViewModelAndroidTest::class,
                    SurvivalGameFragmentTest::class,
                    GameConfigSurvivalFragmentTest::class)
class AndroidTestSuite