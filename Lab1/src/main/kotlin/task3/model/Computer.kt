package task3.model

import kotlinx.coroutines.delay
import task3.enum.SpeakingActionCharacterization
import task3.enum.SpeakingActionMode
import task3.enum.SpeakingActionType
import task3.flow.mapToAggressiveRate
import task3.log.Logger
import java.time.Duration

class Computer private constructor() {
    companion object {
        const val LOGGER_NAME = "COMPUTER"
        const val ACTION_DESTINATION_NAME = "COMPUTER"

        private var instance: Computer? = null
        fun getInstance(): Computer {
            if (instance == null) {
                instance = Computer()
            }
            return instance!!
        }
    }

    suspend fun applyAction(speakingAction: SpeakingAction) {
        delay(Duration.ofSeconds(1L).toMillis())
        val (characterization, rate) = speakingAction.toCharacterizationWithAggressiveRate()
        val aggressiveRate = mapToAggressiveRate(rate)
        Logger.log(
            logger = LOGGER_NAME,
            text = """
                |
                |Action was applied
                |speakingActionType=${speakingAction.type}
                |source=${speakingAction.sourceName}
                |destination=$ACTION_DESTINATION_NAME
                |aggressiveRate=$aggressiveRate
                |characterization=$characterization
                |mode=${speakingAction.mode}
            """.trimMargin()
        )
    }

    fun getAggressiveRate(speakingAction: SpeakingAction): Double {
        return speakingAction.toCharacterizationWithAggressiveRate().second
    }

    private fun SpeakingAction.toCharacterizationWithAggressiveRate(): Pair<SpeakingActionCharacterization, Double> {
        return if (this.sourceName == Ford.ACTION_SOURCE_NAME &&
            this.type == SpeakingActionType.COUNT &&
            this.mode == SpeakingActionMode.ALOUD
        ) {
            SpeakingActionCharacterization.AGGRESSIVE to 0.85
        } else {
            SpeakingActionCharacterization.NONE to 0.0
        }
    }
}

