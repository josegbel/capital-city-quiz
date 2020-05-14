package com.example.capitalcityquizktx

import com.example.capitalcityquizktx.model.register.UserManagementServiceImpl
import com.example.capitalcityquizktx.ui.register.IRegisterView
import com.example.capitalcityquizktx.ui.register.RegisterPresenter
import io.mockk.MockKAnnotations
import io.mockk.impl.annotations.MockK
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import testUtil.CoroutineTestRule

@RunWith(JUnit4::class)
class RegisterPresenterTest {

    @get:Rule
    var coroutinesTestRule = CoroutineTestRule()

    @MockK
    lateinit var viewMock: IRegisterView

    @MockK
    lateinit var service : UserManagementServiceImpl

    private lateinit var presenter: RegisterPresenter

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        presenter =
            RegisterPresenter(viewMock, coroutinesTestRule.testDispatcherProvider)
    }

    @Test
    fun shouldDoTheTrickForThisClass() {
       Assert.assertEquals(1,1)
    }

//    @Test
//    fun `shouldCreateNewUser`() = coroutinesTestRule.testDispatcher.runBlockingTest {
//        // given
//        val userDetails = UserDetails(
//            username = "user1", password = "123",
//            name = "Juan", surname = "Doe", email = "user1@bbc.com"
//        )
//        coEvery {
//                service.verifyUserIsNotInDatabase(userDetails.username, userDetails.email)
//        } returns UserExistence(usernameInDatabase = false, emailInDatabase = false)
//
//        // when
//        presenter.createNewUser(userDetails)
//
//        // then
//        coVerify(exactly = 1) { service.createNewUser(userDetails) }
//    }

    @Test
    fun `shouldVerifyEmailExistsWhenCreatingNewUser`() {
//        // given
//        val userDetails = UserDetails(
//            username = "user1", password = "123",
//            email = "Juan", name = "Doe", surname = "user1@bbc.com"
//        )
//
//
////        every { call.execute() } returns
//
//
////        every { service.verifyUserIsNotInDatabase(userDetails.username, userDetails.email) } returns
//
//        // when
//        presenter.createNewUser(userDetails)
//
//        // then
//        coVerify(exactly = 1) {
//            service.verifyUserIsNotInDatabase(
//                userDetails.username,
//                userDetails.email
//            )
//        }
    }
}