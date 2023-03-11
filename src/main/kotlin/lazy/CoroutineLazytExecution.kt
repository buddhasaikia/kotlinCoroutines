package lazy

import concurrent.getMessageHello
import concurrent.getMessageWorld
import kotlinx.coroutines.*
import kotlin.system.measureTimeMillis

fun main() = runBlocking {
    println("Main program starts: ${Thread.currentThread().name}")
    val time = measureTimeMillis {
        val msg1: Deferred<String> = async(start = CoroutineStart.LAZY) {
            println("$this")
            getMessageHello()
        }
        val msg2: Deferred<String> = async(start = CoroutineStart.LAZY) { getMessageWorld() }
        println("${msg1.await()} ${msg2.await()}")
    }
    println("$this")
    println("Time taken: $time")
    println("\nMain program ends: ${Thread.currentThread().name}")
}

suspend fun getMessageHello1(): String {
    delay(1000)
    println("Inside lazy.getMessageHello1")
    return "Hello"
}

suspend fun getMessageWorld1(): String {
    delay(1000)
    println("Inside getMessageHello2")
    return "World!"
}