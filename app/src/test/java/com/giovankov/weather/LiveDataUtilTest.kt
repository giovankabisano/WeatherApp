package com.giovankov.weather

import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import java.util.concurrent.CountDownLatch
import java.util.concurrent.TimeUnit
import java.util.concurrent.TimeoutException

fun <T> LiveData<T>.getOrAwaitValue(
    time: Long = 2,
    timeUnit: TimeUnit = TimeUnit.SECONDS,
    afterObserve: () -> Unit = {}
): T {
    var data: T? = null
    val latch = CountDownLatch(1)

    val observer = Observer<T> {
        data = it
        latch.countDown()
    }

    observeForever(observer)

    try {
        afterObserve.invoke()

        // Don't wait indefinitely if the LiveData never updates
        if (!latch.await(time, timeUnit)) {
            throw TimeoutException("LiveData value was never set.")
        }

        // We have a value - return it
        @Suppress("UNCHECKED_CAST")
        return data as T
    } finally {
        removeObserver(observer)
    }
}