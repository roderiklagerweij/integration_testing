package com.rl.integrationtestingsample.util


data class MockInterceptor(
    val method: HTTPMethod,
    val url: String
)

data class MockResponse(
    val code: Int,
    val body: String?
)

enum class HTTPMethod {
    GET, POST, DELETE, PUT
}
