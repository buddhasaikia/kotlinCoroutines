package dispatcher

import kotlinx.coroutines.*

fun main() = runBlocking {
    println("Main program starts: ${Thread.currentThread().name}")

    //Without parameter: CONFINED, Confined Dispatcher
    //it inherits the coroutine context from the immediate parent i.e. runBlocking
    //Even after execution of the suspending function it will continue to execute on the main thread
    launch {
        println("c1: ${Thread.currentThread().name}") //Thread: main
        delay(1000)
        println("c1 after delay: ${Thread.currentThread().name}") //Thread: main
    }

    //Similar to GlobalScope.launch{}
    launch (Dispatchers.Default){
        println("c2: ${Thread.currentThread().name}") //Thread: T1
        delay(1000)
        println("c2 after delay: ${Thread.currentThread().name}") //Thread: T1 or some other thread
    }

    //Dispatchers.Unconfined inherits the coroutine context from the immediate parent i.e. runBlocking
    //After execution of the suspending function it will execute on some other thread
    launch (Dispatchers.Unconfined){
        println("c3: ${Thread.currentThread().name}") //Thread: main
        delay(1000)
        println("c3 after delay: ${Thread.currentThread().name}") //Thread: some other thread from shared pool
    }

    //Same as confined dispatcher
    launch (coroutineContext){
        println("c4: ${Thread.currentThread().name}") //Thread: main
        delay(1000)
        println("c4 after delay: ${Thread.currentThread().name}") //Thread: main
    }

    println("Main program ends: ${Thread.currentThread().name}")
}
