package com.example.capitalcityquizktx.ui.register

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.basgeekball.awesomevalidation.AwesomeValidation
import com.basgeekball.awesomevalidation.ValidationStyle
import com.example.capitalcityquizktx.R
import com.example.capitalcityquizktx.databinding.RegisterFragmentBinding
import com.example.capitalcityquizktx.model.register.UserDetails
import kotlinx.android.synthetic.main.register_fragment.*

class RegisterFragment : Fragment(),
    IRegisterView {

    private val presenter : RegisterPresenter =
        RegisterPresenter(this)

    private val awesomeValidation = AwesomeValidation(ValidationStyle.BASIC)

    lateinit var binding : RegisterFragmentBinding

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        addValidations()

        binding.registerRegBtn.setOnClickListener { view ->
            if(awesomeValidation.validate()){
                submitUserData()
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
            inflater, R.layout.register_fragment, container, false)

        return binding.root
    }

    private fun addValidations() {
        // username validation
        awesomeValidation.addValidation(
            activity,
            R.id.usernameRegET, "[A-Za-z0-9]+", R.string.err_username
        )

        // password confirmation
        awesomeValidation.addValidation(
            activity, R.id.confirmPasswordRegET,
            R.id.passwordRegET, R.string.err_confirmation_pw
        )

        // email validation
        val regexEmail = "^([a-zA-Z0-9_\\-\\.]+)@([a-zA-Z0-9_\\-\\.]+)\\.([a-zA-Z]{2,5})$"
        awesomeValidation.addValidation(
            activity,
            R.id.emailRegET, regexEmail, R.string.err_email
        )

        // names validation
        val regexName = "^([ \\u00c0-\\u01ffa-zA-Z\'\\-])+$"
        awesomeValidation.addValidation(
            activity,
            R.id.firstNameRegET, regexName, R.string.err_first_name
        )

        // surname validation
        awesomeValidation.addValidation(
            activity,
            R.id.lastNameRegET, regexName, R.string.err_last_name
        )
    }

    override fun displayEmailInDatabaseError(){
        emailRegET.error = "This email address is already being used"
    }

    override fun displayUsernameInDatabaseError() {
        usernameRegET.error= "This username is already being used"
    }

    override fun displayAccountErrorDialog() {
        FailedRegisterFragment().show(activity!!.fragmentManager, "Error creating account")

    }

    override fun displayUnableToConntectDialog() {
        FailedConnectionFragment().show(activity!!.fragmentManager, "Unable to connect")
    }

    override fun submitUserData() {
        val user = UserDetails(
            binding.usernameRegET.text.toString(),
            binding.passwordRegET.text.toString(),
            binding.emailRegET.text.toString(),
            binding.firstNameRegET.text.toString(),
            binding.lastNameRegET.text.toString()
        )

        presenter.createNewUser(user)
    }
}