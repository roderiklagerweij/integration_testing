package com.rl.integrationtestingsample.data.model

import com.squareup.moshi.Json

data class AuthenticationRequest(
    @Json(name = "username")
    val username : String,

    @Json(name = "password")
    val password : String
)