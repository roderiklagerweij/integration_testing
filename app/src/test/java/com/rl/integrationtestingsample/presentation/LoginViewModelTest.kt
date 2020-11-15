package com.rl.integrationtestingsample.presentation

import android.content.SharedPreferences
import com.rl.integrationtestingsample.domain.usecase.LoginUseCase
import com.rl.integrationtestingsample.util.*
import kotlinx.coroutines.InternalCoroutinesApi
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import javax.inject.Inject

@InternalCoroutinesApi
class LoginViewModelTest : BaseIntegrationTest() {

    @Inject
    lateinit var loginUseCase: LoginUseCase
    @Inject
    lateinit var inMemorySharedPreferences : SharedPreferences

    lateinit var viewModel: LoginViewModel
    lateinit var stateObserver : TestObserver<LoginViewState>

    @Before
    fun beforeTest() {
        applicationComponent.inject(this)
        viewModel = LoginViewModel(loginUseCase)
        stateObserver = viewModel.viewStateObservable.testObserver()
    }

    @Test
    fun `When user enters invalid username a validation and presses login then error is shown`() {
        viewModel.onLoginClicked() // no username/password set so: invalid!

        stateObserver.waitUntil { it is LoginViewState.InvalidInputData }
    }

    @Test
    fun `When user enters an incorrect password and presses login then an error is shown that the password is incorrect`() {
        mocks[MockInterceptor(HTTPMethod.POST, "/login")] = MockResponse(401, "".trimIndent())

        viewModel.usernameText = "test_username"
        viewModel.passwordText = "incorrect_password"
        viewModel.onLoginClicked()

        stateObserver.waitUntil { it is LoginViewState.LoginFailed }
    }

    @Test
    fun `When user logs in successfully then session information is stored on disk`() {
        mocks[MockInterceptor(HTTPMethod.POST, "/login")] = MockResponse(200, """
                {
                    "token": "1234qwer"
                }
            """.trimIndent())

        viewModel.usernameText = "test_username"
        viewModel.passwordText = "test_password"
        viewModel.onLoginClicked()

        stateObserver.waitUntil {
            it is LoginViewState.LoginSuccess
        }

        assertEquals("1234qwer", inMemorySharedPreferences.getString("token", ""))
    }
}