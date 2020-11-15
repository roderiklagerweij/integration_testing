package com.rl.integrationtestingsample.di

import android.app.Application
import com.rl.integrationtestingsample.presentation.LoginFragment
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        ApplicationModule::class]
)
interface ApplicationComponent {

    fun inject(into: LoginFragment)

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder
        fun build(): ApplicationComponent
    }
}
