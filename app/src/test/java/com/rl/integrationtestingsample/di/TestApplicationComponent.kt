package com.rl.integrationtestingsample.di

import com.rl.integrationtestingsample.presentation.LoginViewModelTest
import com.rl.integrationtestingsample.util.BaseIntegrationTest
import dagger.Component
import kotlinx.coroutines.InternalCoroutinesApi
import javax.inject.Singleton

@InternalCoroutinesApi
@Singleton
@Component(
    modules = [
        TestApplicationModule::class]
)
interface TestApplicationComponent {

    fun inject(into: BaseIntegrationTest)
    fun inject(into : LoginViewModelTest)
}
