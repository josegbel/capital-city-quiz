package com.example.capitalcityquizktx.UI.SurvivalMode

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import com.example.capitalcityquizktx.R
import com.example.capitalcityquizktx.databinding.CountriesAmountSelectionFragmentBinding

class CountriesAmountSelectionFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding : CountriesAmountSelectionFragmentBinding = DataBindingUtil.inflate(
            inflater, R.layout.countries_amount_selection_fragment, container, false)

//        binding.allCountriesBtn.setOnClickListener{ v: View -> Navigation.findNavController(v)
//            .navigate(R.id.action_countriesAmountSelectionFragment_to_timeSelectionFragment)}
//
//        binding.twentyCountriesBtn.setOnClickListener{ v: View -> Navigation.findNavController(v)
//            .navigate(R.id.action_countriesAmountSelectionFragment_to_timeSelectionFragment)}
//
//        binding.fiftyCountriesBtn.setOnClickListener{ v: View -> Navigation.findNavController(v)
//            .navigate(R.id.action_countriesAmountSelectionFragment_to_timeSelectionFragment)}
//
//        binding.oneHundredCountriesBtn.setOnClickListener{ v: View -> Navigation.findNavController(v)
//            .navigate(R.id.action_countriesAmountSelectionFragment_to_timeSelectionFragment)}

        return binding.root
    }



}