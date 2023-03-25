
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.currentTime
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

internal class SmartTest {

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun taskTest() = runTest {
        val smart = Smart()
        val startTime = currentTime
        smart.task(5)
        val time = currentTime - startTime
        val complete = time < 100000L
        assertEquals(complete, true)
    }
}