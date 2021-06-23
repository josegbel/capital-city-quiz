package com.example.capitalcityquizktx.ui.gamemodeselection


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import com.example.capitalcityquizktx.R
import com.example.capitalcityquizktx.databinding.GameModeSelectionFragmentBinding

/**
    J. Garcia CapitalCityQuiz in Kotlin 10/12/2019

    Fragment that allows the user to select from different game modes:
        - Survival Mode: Players try to get right as many questions as possible under a timer.

        - Practice Mode: There is no timer in this game mode.
                         Players get questions and get 4 options to select from.

        - Training Mode: The system have been learning from the users' answers.
                         In this game mode the questions are difficult question for a user.
                         Therefore, the questions are tailored to their knowledge.
 */
class GameModeSelectionFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding : GameModeSelectionFragmentBinding = DataBindingUtil.inflate(
            inflater, R.layout.game_mode_selection_fragment, container, false)

        binding.GMSFsurvivalModeBtn.setOnClickListener{ v : View -> Navigation.findNavController(v)
            .navigate(R.id.action_gameModeSelectionFragment_to_gameConfigSurvivalFragment)}

        binding.GMSFpracticeModeBtn.setOnClickListener{ Toast
            .makeText(context, "Under construction", Toast.LENGTH_LONG)
            .show()}

        return binding.root
    }
}
