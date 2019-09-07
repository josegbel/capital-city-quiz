package com.example.capitalcityquizktx.UI.SurvivalMode

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.IdRes
import androidx.databinding.DataBindingUtil
import androidx.navigation.NavDestinationBuilder
import androidx.navigation.NavDirections
import androidx.navigation.Navigation
import com.example.capitalcityquizktx.R
import com.example.capitalcityquizktx.Utils.navigateSafe
import com.example.capitalcityquizktx.databinding.ContinentSelectionFragmentBinding

class ContinentSelectionFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding : ContinentSelectionFragmentBinding = DataBindingUtil.inflate(
            inflater, R.layout.continent_selection_fragment, container, false)


        //TODO to find out why is this buttons requiering null safety
//        binding.africaSMBtn.setOnClickListener{ v: View -> Navigation.findNavController(v)
//            .navigateSafe(R.id.action_continentsAmountSelectionFragment_to_continentSelectionFragment)}
//
//        binding.asiaSMBtn.setOnClickListener{ v: View -> Navigation.findNavController(v)
//            .navigateSafe(R.id.action_continentsAmountSelectionFragment_to_countriesAmountSelectionFragment)}
//
//        binding.australiaSMBtn.setOnClickListener{ v: View -> Navigation.findNavController(v)
//            .navigateSafe(R.id.action_continentsAmountSelectionFragment_to_countriesAmountSelectionFragment)}
//
//        binding.europeSMBtn.setOnClickListener{ v: View -> Navigation.findNavController(v)
//            .navigateSafe(R.id.action_continentsAmountSelectionFragment_to_countriesAmountSelectionFragment)}
//
//        binding.northAmericaSMBtn.setOnClickListener{ v: View -> Navigation.findNavController(v)
//            .navigateSafe(R.id.action_continentsAmountSelectionFragment_to_countriesAmountSelectionFragment)}
//
//        binding.southAmericaSMBtn.setOnClickListener{ v: View -> navigate(R.id.action_continentsAmountSelectionFragment_to_countriesAmountSelectionFragment , v)}

        return binding.root
    }
}