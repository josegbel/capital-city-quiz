package com.example.capitalcityquizktx.data.network.register

import com.example.capitalcityquizktx.data.network.ServiceAPIFactory
import com.example.capitalcityquizktx.data.models.user.UserDetailsSchema

class UserManagementServiceImpl(
    private val service: UserManagementService = ServiceAPIFactory.createService()
){
    suspend fun createNewUser(userDetailsSchema: UserDetailsSchema) : Boolean = service.createUser(userDetailsSchema)
    suspend fun verifyUserIsNotInDatabase(username : String, email : String)
            = service.verifyUserIsNotInDatabase(username, email)
}
