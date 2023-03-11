package sequential

import kotlinx.coroutines.*
import kotlin.system.measureTimeMillis

fun main() = runBlocking {
    println("Main program starts: ${Thread.currentThread().name}")
    val time = measureTimeMillis {
        val msg1 = getMessageOne()
        val msg2 = getMessageTwo()
        println("$msg1 $msg2")
    }
    println("Time taken: $time")
    println("\nMain program ends: ${Thread.currentThread().name}")
}

suspend fun getMessageOne(): String {
    delay(1000)
    return "Hello"
}

suspend fun getMessageTwo(): String {
    delay(1000)
    return "World!"
}