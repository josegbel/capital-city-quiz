package com.example.capitalcityquizktx.ui.survivalmode

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.example.capitalcityquizktx.R
import com.example.capitalcityquizktx.config.SurvivalModeGameConfig
import com.example.capitalcityquizktx.databinding.SurvivalModeGameFragmentBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

/*

    J. Garcia CapitalCityQuiz in Kotlin 2019

 */
class SurvivalModeGameFragment : Fragment() {

    private var gameConfig : SurvivalModeGameConfig? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding : SurvivalModeGameFragmentBinding = DataBindingUtil.inflate(
            inflater, R.layout.survival_mode_game_fragment, container, false)

            val args = SurvivalModeGameFragmentArgs.fromBundle(arguments!!)

        if (gameConfig == null)
            gameConfig = args.survivalModeGameConfig

        Toast.makeText(context, "WELCOME TO THE GAME. WE ARE CURRENTLY WORKING ON THIS PART OF YOUR FAVOURITE GAME. HOWEVER, AT THIS POINT I CAN SAY YOU WOULD LIKE TO PLAY ${gameConfig!!.continents}, ${gameConfig!!.numQuestions} questions, and ${gameConfig!!.timeLimit} seconds per question. SEE YOU SOON :)", Toast.LENGTH_LONG).show()
        val application = requireNotNull(this.activity).application

//        val dataSource = CountryDatabase.getInstance(application).countryDatabaseDao

//        val viewModelFactory = SurvivalModeViewModelFactory(dataSource, application)
//
//        val survivalModeViewModel =
//            ViewModelProviders.of(this, viewModelFactory).get(SurvivalModeViewModel::class.java)
        val survivalModeViewModel : SurvivalModeViewModel by viewModel()

        binding.survivalModeViewModel = survivalModeViewModel

        binding.setLifecycleOwner(this)
        return null
    }
}
