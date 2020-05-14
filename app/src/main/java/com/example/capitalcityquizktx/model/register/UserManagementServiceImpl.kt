package com.example.capitalcityquizktx.model.register

import com.example.capitalcityquizktx.model.ServiceAPIFactory

class UserManagementServiceImpl(
    private val service: IUserManagementService = ServiceAPIFactory.createService()
){
    suspend fun createNewUser(userDetails: UserDetails) : Boolean = service.createUser(userDetails)
    suspend fun verifyUserIsNotInDatabase(username : String, email : String)
            = service.verifyUserIsNotInDatabase(username, email)
}
