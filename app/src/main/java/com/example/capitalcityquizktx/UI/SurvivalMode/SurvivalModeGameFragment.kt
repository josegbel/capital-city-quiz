package com.example.capitalcityquizktx.UI.SurvivalMode


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.example.capitalcityquizktx.R
import com.example.capitalcityquizktx.databinding.SurvivalModeGameFragmentBinding

class SurvivalModeGameFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding : SurvivalModeGameFragmentBinding = DataBindingUtil.inflate(
            inflater, R.layout.survival_mode_game_fragment, container, false)

            val args = SurvivalModeGameFragmentArgs.fromBundle(arguments!!)

        val gameConfig = args.survivalModeGameConfig

        Toast.makeText(context, "WELCOME TO THE GAME. WE ARE CURRENTLY WORKING ON THIS PART OF YOUR FAVOURITE GAME. HOWEVER, AT THIS POINT I CAN SAY YOU WOULD LIKE TO PLAY ${gameConfig.continents}, ${gameConfig.numQuestions} questions, and ${gameConfig.timeLimit} seconds per question. SEE YOU SOON :)", Toast.LENGTH_LONG).show()
        return null
    }

}
