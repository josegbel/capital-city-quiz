package com.example.capitalcityquizktx.ui.register

interface IRegisterView {
    fun submitUserData()
    fun displayEmailInDatabaseError()
    fun displayUsernameInDatabaseError()
    fun displayAccountErrorDialog()
    fun displayUnableToConntectDialog()
}
