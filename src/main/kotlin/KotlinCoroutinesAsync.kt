import kotlinx.coroutines.*

fun main() = runBlocking {
    println("Main program starts: ${Thread.currentThread().name}")
    val jobDeferred: Deferred<String> = async {//Creates a background coroutine that runs on a background thread.
        println("Fake work starts: ${Thread.currentThread().name}")
        mySuspendFunc(1000)
        println("Fake work finished: ${Thread.currentThread().name}")
        "This is a return value"
    }
    val result = jobDeferred.await()
    println("$result")
    //jobDeferred.join()
    println("Main program ends: ${Thread.currentThread().name}")
}

suspend fun mySuspendFunc(time: Long){
    delay(time)
}