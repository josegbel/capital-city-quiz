package com.example.capitalcityquizktx

import TestUtil.MainCoroutineRule
import com.example.capitalcityquizktx.model.register.IUserManagementService
import com.example.capitalcityquizktx.model.register.UserDetails
import com.example.capitalcityquizktx.ui.register.IRegisterView
import com.example.capitalcityquizktx.ui.register.RegisterPresenter
import io.mockk.MockKAnnotations
import io.mockk.impl.annotations.MockK
import io.mockk.verify
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class RegisterPresenterTest {

    @get:Rule
    val coroutineRule = MainCoroutineRule()

    @MockK
    lateinit var viewMock : IRegisterView

    @MockK
    lateinit var service : IUserManagementService

    private lateinit var presenter : RegisterPresenter

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        presenter =
            RegisterPresenter(viewMock)
    }

    @Test
    fun testTest(){
        Assert.assertEquals(2,2)
    }

    @Test
    fun `shouldCreateNewUser`(){
        // given
        val userDetails = UserDetails(username = "user1", password = "123",
            name = "Juan", surname = "Doe", email = "user1@bbc.com")

        // when
        presenter.createNewUser(userDetails)

        // then
        verify (exactly = 1){ service.createUser(userDetails) }
    }

    @Test
    fun `shouldVerifyEmailExistsWhenCreatingNewUser`(){
        // given
        val userDetails = UserDetails(username = "user1", password = "123",
            email = "Juan", name = "Doe", surname = "user1@bbc.com")

        // when
        presenter.createNewUser(userDetails)

        // then
        verify (exactly = 1){ service.verifyUserIsNotInDatabase(userDetails.username, userDetails.email) }
    }
}