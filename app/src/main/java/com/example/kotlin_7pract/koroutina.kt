package com.example.kotlin_7pract

import kotlinx.coroutines.CoroutineStart
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.newFixedThreadPoolContext
import kotlinx.coroutines.newSingleThreadContext
import kotlinx.coroutines.runBlocking


fun main() {
    println("До запуска корутины:${Thread.currentThread().name}")

    GlobalScope.launch(Dispatchers.Unconfined) {
        delay(1000)
        println("Внутри корутины:${Thread.currentThread().name}")
    }
    println("После запуска корутины")

    println("\nДо запуска корутины:${Thread.currentThread().name}")
    val singleThreadDispatcher =
        newSingleThreadContext("SingleThread")
    GlobalScope.launch(singleThreadDispatcher) {
        delay(1000)
        println("Внутри корутины:${Thread.currentThread().name}")
        singleThreadDispatcher.close()
    }
    println("После запуска корутины")


    println("\nДо запуска корутины:${Thread.currentThread().name}")
    val fixedThreadPoolDispatcher =
        newFixedThreadPoolContext(2, "FixedThreadPool")
    GlobalScope.launch(fixedThreadPoolDispatcher) {
        delay(1000)
        println("Внутри корутины:${Thread.currentThread().name}")
        fixedThreadPoolDispatcher.close()
    }
    println("После запуска корутины")

    Thread.sleep(2000)
}