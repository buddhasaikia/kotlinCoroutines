import kotlinx.coroutines.*

fun main() = runBlocking {
    println("Main program starts: ${Thread.currentThread().name}")
    val job: Job = launch(Dispatchers.Default) {
        try {
            for (i in 0..500) {
                //if (!isActive) return@launch
                print("$i.")
                delay(5)
            }
        }catch (e: CancellationException) {
            println("\nException caught successfully. ${e.message}")
        } finally {
            withContext(NonCancellable) {
                println("$this")
                delay(1000)
            }
            println("Close resources in finally.")
        }
    }
    delay(10)
    job.cancel(CancellationException("My cancellation message"))
    job.join()
    println("\nMain program ends: ${Thread.currentThread().name}")
}