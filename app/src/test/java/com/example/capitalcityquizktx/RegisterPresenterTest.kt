package com.example.capitalcityquizktx

import com.example.capitalcityquizktx.model.IRegisterService
import com.example.capitalcityquizktx.model.UserDetails
import com.example.capitalcityquizktx.ui.IRegisterView
import com.example.capitalcityquizktx.ui.RegisterPresenter
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.verify
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4


@RunWith(JUnit4::class)
class RegisterPresenterTest {

    @MockK
    lateinit var viewMock : IRegisterView

    @MockK
    lateinit var service : IRegisterService

    private lateinit var presenter : RegisterPresenter

    @Before
    fun setUp() {
        MockKAnnotations.init()
        presenter = RegisterPresenter(viewMock, service)
    }


    @Test
    fun `shouldCreateNewUser`(){
        // given
        val userDetails = UserDetails(username = "user1", password = "123",
            firstName = "Juan", lastName = "Doe", email = "user1@bbc.com")

        // when
        presenter.createNewUser(userDetails)

        // then
        verify {  }
    }
}