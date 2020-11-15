package com.rl.integrationtestingsample.data.mapper

import com.rl.integrationtestingsample.data.model.AuthenticationRequest
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LoginRequestMapper @Inject constructor() {
    operator fun invoke(username : String, password : String) = AuthenticationRequest(username, password)
}