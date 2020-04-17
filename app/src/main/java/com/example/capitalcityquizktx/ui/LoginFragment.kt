package com.example.capitalcityquizktx.ui


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.example.capitalcityquizktx.R
import com.example.capitalcityquizktx.databinding.LoginFragmentBinding


/**

    J. Garcia CapitalCityQuiz in Kotlin 10/12/2019

 */
class LoginFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding : LoginFragmentBinding = DataBindingUtil.inflate(
            inflater, R.layout.login_fragment, container, false)

        binding.logInBtn.setOnClickListener { v : View -> Navigation.findNavController(v)
            .navigate(LoginFragmentDirections.actionTitleFragmentToGameModeSelectionFragment()) }

        binding.registerBtn.setOnClickListener { v: View -> Navigation.findNavController(v)
            .navigate(LoginFragmentDirections.actionLoginFragmentToRegisterFragment())
        }

        return binding.root
    }
}
