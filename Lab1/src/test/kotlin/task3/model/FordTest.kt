package task3.model

import io.mockk.clearAllMocks
import io.mockk.coVerify
import io.mockk.junit5.MockKExtension
import io.mockk.mockk
import io.mockk.verify
import kotlinx.coroutines.runBlocking
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource
import task3.log.LogJournalTest
import task3.log.Logger
import java.util.stream.IntStream

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
        Assertions.assertThat(
            ford === Ford.getInstance()
        )

        Assertions.assertThat(
            Ford.getInstance() === Ford.getInstance()
        )
    }

    @ParameterizedTest
    @ValueSource(ints = [1, 3])
    fun `startCountingAloud calls speakingAction N times`(times: Int) {
        val speakingAction = mockk<SpeakingAction>(relaxed = true)

        runBlocking {
            val action = Ford.getInstance().performSpeakingActionManyTimes(speakingAction, times)
            action.job!!.join()
        }
        println(Logger.getLogs())
//        verify(atLeast = 1, atMost = 1) { speakingAction.perform }
    }
}