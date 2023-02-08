package task3.model

import io.mockk.clearAllMocks
import io.mockk.junit5.MockKExtension
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import task3.enum.SpeakingActionMode
import task3.enum.SpeakingActionType
import task3.log.Logger

@ExtendWith(value = [MockKExtension::class])
internal class FordTest {
    private var ford: Ford = Ford.getInstance()

    @BeforeEach
    fun tearUp() {
        ford = Ford.getInstance()
        clearAllMocks()
    }

    @Test
    fun `assert singleton`() {
        assertThat(
            ford === Ford.getInstance()
        )

        assertThat(
            Ford.getInstance() === Ford.getInstance()
        )
    }

    @Test
    fun `Ford performSpeakingActionManyTimes N times`(): Unit = runBlocking {
        val times = 3
        val speakingAction = SpeakingAction(
            sourceName = Ford.ACTION_SOURCE_NAME,
            mode = SpeakingActionMode.ALOUD,
            type = SpeakingActionType.COUNT,
        )

        val action = Ford.getInstance().performSpeakingActionManyTimes(speakingAction, times)
        delay(100)
        assertThat(Ford.getInstance().isStillReading()).isEqualTo(true)
        action.job!!.join()
        assertThat(Ford.getInstance().isStillReading()).isEqualTo(false)

        assertThat(Logger.getLogs()).isEqualTo(
            """
                FORD: Start counting for 3 seconds
                FORD: FORD COUNT ALOUD `1`
                FORD: FORD COUNT ALOUD `2`
                FORD: FORD COUNT ALOUD `3`
                FORD: Finish reading
            """.trimIndent()
        )
    }
}
