package com.example.capitalcityquizktx

import com.example.capitalcityquizktx.model.register.IUserManagementService
import com.example.capitalcityquizktx.model.register.UserExistence
import com.example.capitalcityquizktx.ui.register.IRegisterView
import com.example.capitalcityquizktx.ui.register.RegisterPresenter
import io.mockk.MockKAnnotations
import io.mockk.impl.annotations.MockK
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import retrofit2.Call

@RunWith(JUnit4::class)
class RegisterPresenterTest {

//    @get:Rule
//    val coroutineRule = MainCoroutineRule()

//    private val mainThreadSurrogate = newSingleThreadContext("UI thread")

    @MockK
    lateinit var viewMock: IRegisterView

    @MockK
    lateinit var service: IUserManagementService

    @MockK
    lateinit var call: Call<UserExistence>

    private lateinit var presenter: RegisterPresenter

    @Before
    fun setUp() {
//        Dispatchers.setMain(mainThreadSurrogate)

        MockKAnnotations.init(this)
        presenter =
            RegisterPresenter(viewMock)
    }

    @After
    fun tearDown() {
//        Dispatchers.resetMain() // reset main dispatcher to the original Main dispatcher
//        mainThreadSurrogate.close()
    }

        @Test
    fun testTest() {
        Assert.assertEquals(2, 2)
    }

//    @Test
//    fun `shouldCreateNewUser`() = runBlocking {
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
//        coVerify(exactly = 1) { service.createUser(userDetails) }
//    }

//    @Test
//    fun `shouldVerifyEmailExistsWhenCreatingNewUser`() {
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