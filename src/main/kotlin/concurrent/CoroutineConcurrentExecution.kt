package concurrent

import kotlinx.coroutines.*
import kotlin.system.measureTimeMillis

fun main() = runBlocking {
    println("Main program starts: ${Thread.currentThread().name}")
    val time = measureTimeMillis {
        val msg1: Deferred<String> = async { getMessageHello() }
        val msg2: Deferred<String> = async { getMessageWorld() }
        println("${msg1.await()} ${msg2.await()}")
    }
    println("Time taken: $time")
    println("\nMain program ends: ${Thread.currentThread().name}")
}

suspend fun getMessageHello(): String {
    delay(1000)
    return "Hello"
}

suspend fun getMessageWorld(): String {
    delay(1000)
    return "World!"
}