package com.rl.integrationtestingsample.presentation

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rl.integrationtestingsample.domain.model.LoadingResult
import com.rl.integrationtestingsample.domain.usecase.LoginUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch

sealed class LoginViewState() {
    object InvalidInputData : LoginViewState()
    object LoginSuccess : LoginViewState()
    object LoginFailed : LoginViewState()
}

class LoginViewModel(private val loginUseCase: LoginUseCase) : ViewModel() {

    var usernameText : String = ""
    var passwordText : String = ""
    val viewStateObservable = MutableLiveData<LoginViewState>()

    @InternalCoroutinesApi
    fun onLoginClicked() {
        if (usernameText.isEmpty() || passwordText.isEmpty()) {
            viewStateObservable.postValue(LoginViewState.InvalidInputData)
        } else {
            viewModelScope.launch {
                loginUseCase(usernameText, passwordText)
                    .flowOn(Dispatchers.IO)
                    .collect {
                        when (it) {
                            is LoadingResult.Success -> {
                                viewStateObservable.postValue(LoginViewState.LoginSuccess)
                            }
                            is LoadingResult.Error -> {
                                viewStateObservable.postValue(LoginViewState.LoginFailed)
                            }
                        }
                    }
            }
        }
    }
}