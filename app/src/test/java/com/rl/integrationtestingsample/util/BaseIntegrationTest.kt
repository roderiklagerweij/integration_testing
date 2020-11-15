package com.rl.integrationtestingsample.util

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.rl.integrationtestingsample.di.DaggerTestApplicationComponent
import com.rl.integrationtestingsample.di.TestApplicationComponent
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.newSingleThreadContext
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import okhttp3.mockwebserver.Dispatcher
import okhttp3.mockwebserver.MockWebServer
import okhttp3.mockwebserver.RecordedRequest
import org.junit.After
import org.junit.Before
import org.junit.Rule

@InternalCoroutinesApi
@ExperimentalCoroutinesApi
open class BaseIntegrationTest {

    // Replaces Android's main looper
    @get:Rule
    val rule = InstantTaskExecutorRule()

    private val mainThreadSurrogate = newSingleThreadContext("UI thread")

    lateinit var applicationComponent : TestApplicationComponent
    lateinit var mockWebServer : MockWebServer
    val mocks = HashMap<MockInterceptor, MockResponse>()

    @Before
    fun setup() {
        Dispatchers.setMain(mainThreadSurrogate)
        mocks.clear()

        val dispatcher = object : Dispatcher() {
            @Throws(InterruptedException::class)
            override fun dispatch(request: RecordedRequest): okhttp3.mockwebserver.MockResponse {

                mocks.forEach {
                    if (it.key.url == request.path && it.key.method.toString() == request.method) {
                        return okhttp3.mockwebserver.MockResponse().setResponseCode(it.value.code).setBody(it.value.body!!)
                    }
                }

                return okhttp3.mockwebserver.MockResponse().setResponseCode(500)
            }
        }

        mockWebServer = MockWebServer()
        mockWebServer.dispatcher = dispatcher
        mockWebServer.start(49812)

        applicationComponent = DaggerTestApplicationComponent.builder().build()
        applicationComponent.inject(this)
    }

    @After
    fun cleanup() {
        mockWebServer.shutdown()
        Dispatchers.resetMain()
        mainThreadSurrogate.close()
    }
}
