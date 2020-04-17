package com.example.capitalcityquizktx.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.example.capitalcityquizktx.R
import com.example.capitalcityquizktx.databinding.RegisterFragmentBinding

class RegisterFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding : RegisterFragmentBinding = DataBindingUtil.inflate(
            inflater, R.layout.register_fragment, container, false)

        return binding.root
    }
}