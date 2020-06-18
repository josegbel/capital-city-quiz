package com.example.capitalcityquizktx.ui.register

import com.example.capitalcityquizktx.data.models.user.UserDetailsSchema
import com.example.capitalcityquizktx.data.models.user.UserExistenceSchema
import com.example.capitalcityquizktx.data.network.register.UserManagementServiceImpl
import com.example.capitalcityquizktx.common.utils.DefaultDispatcherProvider
import com.example.capitalcityquizktx.common.utils.DispatcherProvider
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.net.ConnectException

class RegisterPresenter(val view: IRegisterView,
                        private val dispatchers: DispatcherProvider = DefaultDispatcherProvider()){

    private val userManagementServiceImpl = UserManagementServiceImpl()

    fun createNewUser(userDetailsSchema: UserDetailsSchema) {

        // verify the user does not exist in database
        var userExistenceSchema: UserExistenceSchema? = null
        GlobalScope.launch(dispatchers.default()) {
            try {
                userExistenceSchema = userManagementServiceImpl
                    .verifyUserIsNotInDatabase(userDetailsSchema.username, userDetailsSchema.email)

                withContext(dispatchers.main()) {
                    if (userExistenceSchema != null) {
                        if (userExistenceSchema!!.emailInDatabase) {
                            view.displayEmailInDatabaseError()
                        }

                        if (userExistenceSchema!!.usernameInDatabase) {
                            view.displayUsernameInDatabaseError()
                        }

                        if (!userExistenceSchema!!.usernameInDatabase && !userExistenceSchema!!.emailInDatabase) {
                            GlobalScope.launch(dispatchers.default()) {
                                try {
                                    val userCreated = userManagementServiceImpl.createNewUser(userDetailsSchema)

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