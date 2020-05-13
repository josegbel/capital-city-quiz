package com.example.capitalcityquizktx.ui.register

import com.example.capitalcityquizktx.model.register.UserDetails
import com.example.capitalcityquizktx.model.register.UserExistence
import com.example.capitalcityquizktx.model.register.UserManagementServiceImpl
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.net.ConnectException


class RegisterPresenter(val view: IRegisterView) {

    private val userManagementServiceImpl = UserManagementServiceImpl()

    fun createNewUser(userDetails: UserDetails) {

        // verify the user does not exist in database
        var userExistence: UserExistence? = null
        GlobalScope.launch(Dispatchers.Default) {
            try {
                userExistence = userManagementServiceImpl
                    .verifyUserIsNotInDatabase(userDetails.username, userDetails.email)
            } catch (e: ConnectException) {
                view.displayUnableToConntectDialog()
            }
        }

        if (userExistence != null) {
            if (userExistence!!.emailInDatabase) {
                view.displayEmailInDatabaseError()
            }

            if (userExistence!!.usernameInDatabase) {
                view.displayUsernameInDatabaseError()
            }

            if (!userExistence!!.usernameInDatabase && !userExistence!!.emailInDatabase) {
                var userCreated: Boolean? = null

                GlobalScope.launch(Dispatchers.Default) {
                    try {
                        userCreated = userManagementServiceImpl.createNewUser(userDetails)
                    } catch (e: ConnectException) {
                        view.displayUnableToConntectDialog()
                    }
                }

                when (userCreated) {
                    true -> 1 // TODO Go to user successfully created screen
                    false -> view.displayAccountErrorDialog()
                }
            }
        }
    }
}