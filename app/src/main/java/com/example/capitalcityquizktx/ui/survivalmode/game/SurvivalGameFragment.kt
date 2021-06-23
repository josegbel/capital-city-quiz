package com.example.capitalcityquizktx.ui.survivalmode.game

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.example.capitalcityquizktx.R
import com.example.capitalcityquizktx.data.models.config.SurvivalGameConfig
import com.example.capitalcityquizktx.databinding.SurvivalGameFragmentBinding
import com.example.capitalcityquizktx.data.models.geographical.Country
import com.example.capitalcityquizktx.domain.viewmodels.SurvivalViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

/**

    J. Garcia CapitalCityQuiz in Kotlin 2019

 */
class SurvivalGameFragment : Fragment(), ISurvivalGameStatus {

    private lateinit var binding : SurvivalGameFragmentBinding

    override fun gameWon() {
        // Navigate to gameWon fragment
    }

    override fun gameOver() {
        // Navigate to gameOver fragment
    }

    private var gameConfig : SurvivalGameConfig? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(
            inflater, R.layout.survival_game_fragment, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        val args = SurvivalGameFragmentArgs.fromBundle(requireArguments())

        if (gameConfig == null)
            gameConfig = args.survivalGameConfig

        val survivalViewModel by viewModel<SurvivalViewModel>()
        binding.lifecycleOwner = this
        binding.survivalViewModel = survivalViewModel

        val myObserver = Observer<List<Country>>{ countries ->
            if (countries != null && countries.isNotEmpty()) {
                binding.countryTextView.text = countries[0].countryName
            }
        }
        survivalViewModel.countries.observe(viewLifecycleOwner, myObserver)
        survivalViewModel.repopulateCountriesInCache()
        survivalViewModel.getCountriesFrom(gameConfig?.continents ?: emptyList())
    }
}
