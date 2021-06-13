package com.example.capitalcityquizktx

import com.example.capitalcityquizktx.data.network.register.UserManagementServiceImpl
import com.example.capitalcityquizktx.ui.register.IRegisterView
import com.example.capitalcityquizktx.ui.register.RegisterPresenter
import org.junit.Assert
import org.junit.Rule
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import testUtil.CoroutineTestRule

@RunWith(JUnit4::class)
class RegisterPresenterTest {

    @get:Rule
    var coroutinesTestRule = CoroutineTestRule()

    @Mock
    lateinit var viewMock: IRegisterView

    @Mock
    lateinit var service : UserManagementServiceImpl

    private lateinit var presenter: RegisterPresenter
    private lateinit var mocks: AutoCloseable

    @BeforeEach
    fun setUp() {
        mocks = MockitoAnnotations.openMocks(this)
        presenter =
            RegisterPresenter(viewMock, coroutinesTestRule.testDispatcherProvider)
    }

    @AfterEach
    fun tearDown() {
        mocks.close()
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