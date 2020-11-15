package com.rl.integrationtestingsample.domain.model

sealed class LoadingResult<out T : Any> {

    data class Success<out T : Any>(val data: T) : LoadingResult<T>()
    data class Error(val exception: Exception) : LoadingResult<Nothing>()

}