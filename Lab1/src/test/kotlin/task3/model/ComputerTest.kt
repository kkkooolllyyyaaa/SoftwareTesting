package task3.model

import kotlinx.coroutines.runBlocking
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import task3.enum.AggressiveRate
import task3.enum.SpeakingActionMode
import task3.enum.SpeakingActionType
import task3.flow.mapToAggressiveRate
import task3.log.Logger

internal class ComputerTest {
    @BeforeEach
    fun tearUp() {
        Logger.clear()
    }

    @Test
    fun `assert singleton`() {
        assertThat(
            Computer.getInstance() === Computer.getInstance()
        )
        val saved = Computer.getInstance()

        assertThat(
            Computer.getInstance() === saved
        )
    }

    @Test
    fun `applyAction works`(): Unit = runBlocking {
        val speakingAction = SpeakingAction(
            sourceName = Ford.ACTION_SOURCE_NAME,
            mode = SpeakingActionMode.ALOUD,
            type = SpeakingActionType.COUNT,
        )

        Computer.getInstance().applyAction(speakingAction)

        val gotLogs = Logger.getLogs()
        assertThat(gotLogs).isEqualTo(
            """
                COMPUTER: 
                Action was applied
                speakingActionType=COUNT
                source=FORD
                destination=COMPUTER
                aggressiveRate=ONE_OF_THE_MOST
                characterization=AGGRESSIVE
                mode=ALOUD
            """.trimIndent()
        )
    }

    @Test
    fun `getAggressiveRate aloud count from ford is on of the most aggressive`() {
        val speakingAction = SpeakingAction(
            sourceName = Ford.ACTION_SOURCE_NAME,
            mode = SpeakingActionMode.ALOUD,
            type = SpeakingActionType.COUNT,
        )

        val rate = Computer.getInstance().getAggressiveRate(speakingAction)
        assertThat(mapToAggressiveRate(rate)).isEqualTo(AggressiveRate.ONE_OF_THE_MOST)
    }

    @Test
    fun `getAggressiveRate from any speaking action small aggressiveRate`() {
        val speakingAction = SpeakingAction(
            sourceName = "Any",
            mode = SpeakingActionMode.values().random(),
            type = SpeakingActionType.values().random(),
        )

        val rate = Computer.getInstance().getAggressiveRate(speakingAction)
        assertThat(mapToAggressiveRate(rate)).isEqualTo(AggressiveRate.SMALL)
    }
}
