package com.example.capitalcityquizktx.ui.register

import com.example.capitalcityquizktx.model.register.UserDetails
import com.example.capitalcityquizktx.model.register.UserExistence
import com.example.capitalcityquizktx.model.register.UserManagementServiceImpl
import com.example.capitalcityquizktx.utils.DefaultDispatcherProvider
import com.example.capitalcityquizktx.utils.DispatcherProvider
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.net.ConnectException


class RegisterPresenter(val view: IRegisterView,
                        private val dispatchers: DispatcherProvider = DefaultDispatcherProvider()){

    private val userManagementServiceImpl = UserManagementServiceImpl()

    fun createNewUser(userDetails: UserDetails) {

        // verify the user does not exist in database
        var userExistence: UserExistence? = null
        GlobalScope.launch(dispatchers.default()) {
            try {
                userExistence = userManagementServiceImpl
                    .verifyUserIsNotInDatabase(userDetails.username, userDetails.email)

                withContext(dispatchers.main()) {
                    if (userExistence != null) {
                        if (userExistence!!.emailInDatabase) {
                            view.displayEmailInDatabaseError()
                        }

                        if (userExistence!!.usernameInDatabase) {
                            view.displayUsernameInDatabaseError()
                        }

                        if (!userExistence!!.usernameInDatabase && !userExistence!!.emailInDatabase) {
                            GlobalScope.launch(dispatchers.default()) {
                                try {
                                    val userCreated = userManagementServiceImpl.createNewUser(userDetails)

                                    withContext(dispatchers.main()) {
                                        when (userCreated) {
                                            true -> 1 // TODO Go to user successfully created screen
                                            false -> view.displayAccountErrorDialog()
                                        }
                                    }
                                } catch (e: ConnectException) {
                                    view.displayUnableToConntectDialog()
                                }
                            }
                        }
                    }
                }
            } catch (e: ConnectException) {
                view.displayUnableToConntectDialog()
            }
        }
    }
}