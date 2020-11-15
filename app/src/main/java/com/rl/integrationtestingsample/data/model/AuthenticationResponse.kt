package com.rl.integrationtestingsample.data.model

import com.squareup.moshi.Json

data class AuthenticationResponse(
    @Json(name = "token")
    val token: String
)