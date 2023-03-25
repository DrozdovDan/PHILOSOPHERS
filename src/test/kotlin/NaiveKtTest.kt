
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith

internal class NaiveKtTest {

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun taskTest() = runTest {
        val naive = Naive()
        val exception = assertFailsWith<Exception> { naive.task(5) }
        assertEquals("BLOCK", exception.message)
    }
}
