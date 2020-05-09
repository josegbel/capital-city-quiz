package com.example.capitalcityquizktx.ui.register

interface IRegisterView {
    fun submitUserData()
    fun emailIsInDatabaseValidation()
    fun usernameInDatabaseValidation()
    fun displayAccountErrorDialog()
    fun displayUnableToConntectDialog()
}
