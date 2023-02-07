package task3.model

import kotlinx.coroutines.delay
import task3.enum.SpeakingActionCharacterization
import task3.enum.SpeakingActionMode
import task3.enum.SpeakingActionType
import task3.log.LogJournal
import java.time.Duration

class Computer private constructor() {
    companion object {
        const val LOGGER_NAME = "COMPUTER"

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
        val characterization = speakingAction.toCharacterization()
        LogJournal.log(
            LOGGER_NAME,
            "${characterization.first} ${speakingAction.type} was applied to $LOGGER_NAME from ${speakingAction.sourceName}"
        )
        println("Компьютеру было применено: $speakingAction")
    }

    private fun SpeakingAction.toCharacterization(): Pair<SpeakingActionCharacterization, Double> {
        return if (this.sourceName == Ford.ACTION_SOURCE_NAME &&
            this.type == SpeakingActionType.COUNT &&
            this.mode == SpeakingActionMode.ALOUD
        ) {
            SpeakingActionCharacterization.AGRESSIVE to 0.99
        } else {
            SpeakingActionCharacterization.NONE to 0.0
        }
    }
}

