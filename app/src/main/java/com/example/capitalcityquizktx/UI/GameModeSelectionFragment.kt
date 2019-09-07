package com.example.capitalcityquizktx.UI


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
 * A simple [Fragment] subclass.
 *
 */
class GameModeSelectionFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding : GameModeSelectionFragmentBinding = DataBindingUtil.inflate(
            inflater, R.layout.game_mode_selection_fragment, container, false)

        binding.survivalModeBtn.setOnClickListener{ v : View -> Navigation.findNavController(v)
            .navigate(R.id.action_gameModeSelectionFragment_to_gameConfigSurvivalModeFragment)}

        binding.practiceModeBtn.setOnClickListener{ Toast
            .makeText(context, "Under construction", Toast.LENGTH_LONG)
            .show()}

        return binding.root
    }

}
