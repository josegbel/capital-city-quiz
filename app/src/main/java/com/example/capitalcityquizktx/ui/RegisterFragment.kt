package com.example.capitalcityquizktx.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.example.capitalcityquizktx.R
import com.example.capitalcityquizktx.databinding.RegisterFragmentBinding
import com.example.capitalcityquizktx.model.UserDetails

class RegisterFragment : Fragment(), IRegisterView {

    val presenter : RegisterPresenter = RegisterPresenter(this)

    lateinit var binding : RegisterFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
            inflater, R.layout.register_fragment, container, false)

        binding.registerRegBtn.setOnClickListener { view ->
            submitUserData()
        }

        return binding.root
    }

    override fun submitUserData() {
        val user = UserDetails(binding.usernameRegET.text.toString(),
            binding.passwordRegET.text.toString(),
            binding.emailRegET.text.toString(),
            binding.firstNameRegET.text.toString(),
            binding.lastNameRegET.text.toString()
        )

        presenter.createNewUser(user)
    }
}