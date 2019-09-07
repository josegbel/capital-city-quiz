package com.example.capitalcityquizktx.UI.SurvivalMode

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import com.example.capitalcityquizktx.R
import com.example.capitalcityquizktx.databinding.TimeSelectionFragmentBinding

class TimesetSelectionFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding : TimeSelectionFragmentBinding = DataBindingUtil.inflate(
            inflater, R.layout.time_selection_fragment, container, false)

        return binding.root
    }

}