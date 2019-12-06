package com.example.capitalcityquizktx.ui.survivalmode

import com.example.capitalcityquizktx.config.GameConfig
import com.example.capitalcityquizktx.model.database.Country

sealed class SurvivalModeState {
    abstract val gameConfig:GameConfig
    abstract val loadedAllItems:Boolean
    abstract val data: List<Country>
}
data class LoadingState(override val gameConfig:GameConfig, override val loadedAllItems: Boolean, override val data: List<Country>) : SurvivalModeState()
data class DefaultState(override val gameConfig:GameConfig, override val loadedAllItems: Boolean, override val data: List<Country>) : SurvivalModeState()
data class ConfiguringState(override val gameConfig:GameConfig, override val loadedAllItems: Boolean, override val data: List<Country>) : SurvivalModeState()
data class ErrorState(val errorMessage: String, override val gameConfig:GameConfig, override val loadedAllItems: Boolean, override val data: List<Country>) : SurvivalModeState()
