package com.example.capitalcityquizktx.UI.SurvivalMode

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import com.example.capitalcityquizktx.R
import com.example.capitalcityquizktx.databinding.ContinentsAmountSelectionFragmentBinding

class ContinentsAmountSelectionFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding : ContinentsAmountSelectionFragmentBinding = DataBindingUtil.inflate(
            inflater, R.layout.continents_amount_selection_fragment, container, false)

//        binding.oneContinentsSMBtn.setOnClickListener{ v: View -> Navigation.findNavController(v)
//            .navigate(R.id.action_continentsAmountSelectionFragment_to_continentSelectionFragment)}
//
//        binding.allContinentsSMBtn.setOnClickListener{ v: View -> Navigation.findNavController(v)
//            .navigate(R.id.action_continentsAmountSelectionFragment_to_countriesAmountSelectionFragment)}

        return binding.root
    }

}