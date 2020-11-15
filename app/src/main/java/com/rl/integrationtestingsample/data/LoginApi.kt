package com.rl.integrationtestingsample.data

import com.rl.integrationtestingsample.data.model.AuthenticationRequest
import com.rl.integrationtestingsample.data.model.AuthenticationResponse
import retrofit2.http.Body
import retrofit2.http.POST

interface LoginApi {

    @POST("/login")
    suspend fun login(@Body authenticationRequest: AuthenticationRequest) : AuthenticationResponse

}