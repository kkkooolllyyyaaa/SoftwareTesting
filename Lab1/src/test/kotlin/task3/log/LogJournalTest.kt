package task3.log

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Test
import kotlin.random.Random

internal class LogJournalTest {
    private var firstInitialized: Logger = Logger.getInstance()

    @AfterEach
    internal fun tearDown() {
        Logger.clear()
    }

    @Test
    fun `assert singleton`() {
        assertThat(
            firstInitialized === Logger.getInstance()
        )

        assertThat(
            Logger.getInstance() === Logger.getInstance()
        )
    }

    @Test
    fun `assert message format`() {
        val loggerName = "LOGGER"
        Logger.log(loggerName, "log1")
        Logger.log(loggerName, "log2")
        Logger.log(loggerName, "log3")
        Logger.log(loggerName, "log4")

        val actualLogs = Logger.getLogs()

        assertThat(actualLogs).isEqualTo(
            "$loggerName: log1\n" +
                    "$loggerName: log2\n" +
                    "$loggerName: log3\n" +
                    "$loggerName: log4"
        )
    }

    @Test
    fun `assert logs appending`() {
        val random = Random.nextLong()
        val beforeAppending = Logger.getLogs()
        assertThat(beforeAppending).doesNotContain(random.toString())

        Logger.log("LOGGER", random.toString())
        val afterAppending = Logger.getLogs()
        assertThat(afterAppending).contains(random.toString())
    }
}