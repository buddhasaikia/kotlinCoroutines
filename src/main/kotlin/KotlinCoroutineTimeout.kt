import kotlinx.coroutines.*

fun main() = runBlocking {
    println("Main program starts: ${Thread.currentThread().name}")
    val result:String? = withTimeoutOrNull(2000) {
        for (i in 0..500) {
            //if (!isActive) return@launch
            print("$i.")
            delay(500)
        }
        "I am done!"
    }
    println("Result: $result")
    println("\nMain program ends: ${Thread.currentThread().name}")
}