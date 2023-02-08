package task3.flow

import exceptions.AggressiveRateValidationException
import kotlinx.coroutines.runBlocking
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource
import task3.enum.*
import task3.model.*

internal class AggressiveRateMappingKtTest {

    @ParameterizedTest
    @CsvSource(
        "0.0, SMALL",
        "0.01, SMALL",
        "0.13, SMALL",
        "0.24, SMALL",
        "0.25, SMALL",

        "0.2501, MEDIUM",
        "0.26, MEDIUM",
        "0.31, MEDIUM",
        "0.46, MEDIUM",
        "0.50, MEDIUM",

        "0.501, HIGH",
        "0.56, HIGH",
        "0.60, HIGH",
        "0.749, HIGH",
        "0.75, HIGH",

        "0.75001, ONE_OF_THE_MOST",
        "0.81, ONE_OF_THE_MOST",
        "0.90, ONE_OF_THE_MOST",
        "0.99, ONE_OF_THE_MOST",
        "1.0, ONE_OF_THE_MOST",
    )
    fun `mapToAggressiveRate returns expected AggressiveRate`(
        rate: Double,
        expectedAggressiveRate: AggressiveRate,
    ) {
        val actual = mapToAggressiveRate(rate)

        assertThat(actual).isEqualTo(expectedAggressiveRate)
    }

    @ParameterizedTest
    @CsvSource(
        "-0.0000001",
        "-0.01",
        "-1.0",
        "-1000",

        "1.0000001",
        "1.2",
        "2.01",
        "123321",
    )
    fun `mapToAggressiveRate throws AggressiveRateValidationException`(rate: Double) {
        assertThrows<AggressiveRateValidationException> {
            mapToAggressiveRate(rate)
        }
    }

    @Test
    fun `toCharacterizationWithAggressiveRate returns AGRESSIVE`() {
        val human = Human()
        val sayAction = SpeakingAction(
            sourceName = human.getName(),
            mode = SpeakingActionMode.ALOUD,
            type = SpeakingActionType.SAY,
        )

        val secondHuman = Human()
        val movementAction = MovementAction(
            to = secondHuman,
            mode = MoveMode.SLOWLY,
        )

        val (characterization, rate) = sayAction.toCharacterizationWithAggressiveRate(movementAction)
        assertThat(characterization).isEqualTo(SpeakingActionCharacterization.AGGRESSIVE)
    }

    @ParameterizedTest
    @CsvSource(
        "ALOUD, SAY, NORMAL",
        "ALOUD, SAY, FAST",

        "ALOUD, COUNT, SLOWLY",
        "ALOUD, COUNT, NORMAL",
        "ALOUD, COUNT, FAST",

        "ALOUD, SWEAR, SLOWLY",
        "ALOUD, SWEAR, NORMAL",
        "ALOUD, SWEAR, FAST",


        "SILENTLY, SAY, SLOWLY",
        "SILENTLY, SAY, NORMAL",
        "SILENTLY, SAY, FAST",

        "SILENTLY, COUNT, SLOWLY",
        "SILENTLY, COUNT, NORMAL",
        "SILENTLY, COUNT, FAST",

        "SILENTLY, SWEAR, SLOWLY",
        "SILENTLY, SWEAR, NORMAL",
        "SILENTLY, SWEAR, FAST",
    )
    fun `toCharacterizationWithAggressiveRate returns None`(
        speakingMode: SpeakingActionMode,
        type: SpeakingActionType,
        moveMode: MoveMode,
    ) {
        val human = Human()
        val sayAction = SpeakingAction(
            sourceName = human.getName(),
            mode = SpeakingActionMode.SILENTLY,
            type = SpeakingActionType.SAY,
        )

        val secondHuman = Human()
        val movementAction = MovementAction(
            to = secondHuman,
            mode = MoveMode.SLOWLY,
        )

        val (characterization, rate) = sayAction.toCharacterizationWithAggressiveRate(movementAction)
        assertThat(characterization).isEqualTo(SpeakingActionCharacterization.NONE)
    }

    @Test
    fun `Count loud to Computer AggressiveRate equal to slowly move saying 'die' AggressiveRate`(): Unit = runBlocking {
        val countLoudAction = SpeakingAction(
            sourceName = Ford.ACTION_SOURCE_NAME,
            mode = SpeakingActionMode.ALOUD,
            type = SpeakingActionType.COUNT,
        )
        val action = Ford.getInstance().performSpeakingActionManyTimes(countLoudAction, 1)

        val human1 = Human("Undefined_1")
        val human2 = Human("Undefined_2")

        val movementAction = MovementAction(
            from = human1,
            to = human2,
            mode = MoveMode.SLOWLY,
        )

        val sayDieAction = SpeakingAction(
            sourceName = human1.getName(),
            mode = SpeakingActionMode.ALOUD,
            type = SpeakingActionType.SAY,
        )

        action.job?.join()
        val job = MovementActionPerformer().performMovementActionSaying(
            movementAction = movementAction,
            times = 1,
            speakingAction = sayDieAction,
            text = "Die..."
        )
        job.join()

        val slowlyMoveSayingDieRate = sayDieAction.toCharacterizationWithAggressiveRate(movementAction).second
        val slowlyMoveSayingDieAggressiveRate = mapToAggressiveRate(slowlyMoveSayingDieRate)

        val countLoudRate = Computer.getInstance().getAggressiveRate(countLoudAction)
        val countLoudAggressiveRate = mapToAggressiveRate(countLoudRate)

        assertThat(slowlyMoveSayingDieAggressiveRate).isEqualTo(countLoudAggressiveRate)
        assertThat(slowlyMoveSayingDieAggressiveRate).isEqualTo(AggressiveRate.ONE_OF_THE_MOST)
    }
}