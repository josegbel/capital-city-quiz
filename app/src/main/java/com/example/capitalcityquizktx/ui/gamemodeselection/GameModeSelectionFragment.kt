package com.example.capitalcityquizktx.ui.gamemodeselection


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.compose.rememberNavController
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

    val binding : GameModeSelectionFragmentBinding by lazy {
        GameModeSelectionFragmentBinding.inflate(layoutInflater)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding.gameModeSelectionSurvivalModeBtn.setOnClickListener{ v : View -> Navigation.findNavController(v)
            .navigate(R.id.action_gameModeSelectionFragment_to_gameConfigSurvivalFragment)}

        binding.gameModeSelectionPracticeBtn.setOnClickListener{ Toast
            .makeText(context, "Under construction", Toast.LENGTH_LONG)
            .show()}

        return binding.root
    }
}

