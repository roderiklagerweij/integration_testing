package com.rl.integrationtestingsample.data

import com.rl.integrationtestingsample.data.mapper.LoginRequestMapper
import com.rl.integrationtestingsample.domain.model.LoadingResult
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LoginRepo @Inject constructor(
    private val loginApi: LoginApi,
    private val mapper : LoginRequestMapper
) {

    suspend fun login(username : String, password : String) = flow {
        try {
            val result = loginApi.login(mapper(username, password))
            emit(LoadingResult.Success(result))
        } catch (e : Exception) {
            emit(LoadingResult.Error(e))
        }
    }
}