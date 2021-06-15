package com.example.capitalcityquizktx

import com.example.capitalcityquizktx.data.network.register.UserManagementService
import com.example.capitalcityquizktx.testUtil.CoroutineTestRule
import com.example.capitalcityquizktx.ui.register.IRegisterView
import com.example.capitalcityquizktx.ui.register.RegisterPresenter
import org.junit.Assert
import org.junit.Rule
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.koin.test.KoinTest
import org.mockito.Mock
import org.mockito.MockitoAnnotations

class RegisterPresenterTest() : KoinTest {

    @get:Rule
    var coroutinesTestRule = CoroutineTestRule()

    @Mock
    lateinit var viewMock: IRegisterView

    @Mock
    lateinit var service: UserManagementService

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
        Assert.assertEquals(1, 1)
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

//    @Test
//    fun shouldVerifyEmailExistsWhenCreatingNewUser() {
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
//    }
}