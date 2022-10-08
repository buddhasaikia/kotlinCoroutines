import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Test

class SimpleTest {
    @Test
    fun myFirstTest() = runBlocking {
        mySuspendFunc(1000)
        Assert.assertEquals(10, 5+5)
    }
}