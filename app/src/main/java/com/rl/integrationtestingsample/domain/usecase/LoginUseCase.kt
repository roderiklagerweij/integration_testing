package com.rl.integrationtestingsample.domain.usecase

import android.content.SharedPreferences
import com.rl.integrationtestingsample.data.LoginRepo
import com.rl.integrationtestingsample.domain.model.LoadingResult
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LoginUseCase @Inject constructor(
    private val loginRepo: LoginRepo,
    private val sharedPreferences: SharedPreferences
) {

    @InternalCoroutinesApi
    suspend operator fun invoke(username : String, password : String) = flow {
        loginRepo.login(username, password).collect { result ->
            when(result) {
                is LoadingResult.Success -> {
                    sharedPreferences.edit().putString("token", result.data.token).commit()
                    emit(result)
                }
                is LoadingResult.Error -> {
                    emit(result)
                }
            }
        }
    }
}
