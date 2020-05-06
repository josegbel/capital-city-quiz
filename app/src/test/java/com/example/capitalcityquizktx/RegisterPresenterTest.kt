package com.example.capitalcityquizktx

import com.example.capitalcityquizktx.ui.register.IRegisterView
import com.example.capitalcityquizktx.ui.register.RegisterPresenter
import io.mockk.MockKAnnotations
import io.mockk.impl.annotations.MockK
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class RegisterPresenterTest {

    @MockK
    lateinit var viewMock : IRegisterView

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

//    @Test
//    fun `shouldCreateNewUser`(){
//        // given
//        val userDetails = UserDetails(username = "user1", password = "123",
//            email = "Juan", name = "Doe", surname = "user1@bbc.com")
//
//        // when
//        presenter.createNewUser(userDetails)
//
//        // then
//        verify {  }
//    }
}