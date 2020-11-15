package com.rl.integrationtestingsample.util

import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import org.junit.Assert.fail

class TestObserver<T>() : Observer<T> {
    val observedValues = mutableListOf<T?>()

    override fun onChanged(value: T?) {
        observedValues.add(value)
    }

    fun waitUntil(timeout : Long = 1000L, operation : (value : T?) -> Boolean) {
        val startTime = System.currentTimeMillis()
        while(true) {
            if (observedValues.isNotEmpty()) {
                if (operation(observedValues.last())) {
                    observedValues.clear()
                    break
                }
            }

            Thread.sleep(5)
            if (System.currentTimeMillis() - startTime > timeout) {
                fail("Operation timeout!")
            }
        }
    }
}
fun <T> LiveData<T>.testObserver() =
    TestObserver<T>().also {
    observeForever(it)
}
