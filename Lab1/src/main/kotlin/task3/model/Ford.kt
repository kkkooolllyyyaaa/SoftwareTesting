package task3.model

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import task3.enum.SpeakingActionCharacterization
import task3.enum.SpeakingActionMode
import task3.enum.SpeakingActionType
import task3.log.LogJournal
import java.time.Duration

class Ford private constructor() {
    private var readingState: ReadingState? = null

    companion object {
        const val LOGGER_NAME = "FORD"
        const val ACTION_SOURCE_NAME = "FORD"

        private var instance: Ford? = null
        fun getInstance(): Ford {
            if (instance == null) {
                instance = Ford()
            }
            return instance!!
        }
    }

    suspend fun startCountingAloud(count: Int): SpeakingAction {
        val mode = SpeakingActionMode.ALOUD
        println("Форд начал cчитать ${mode.humanReadableRu} на протяжении $count секунд")
        LogJournal.log(LOGGER_NAME, "Start reading for $count seconds")
        val job = GlobalScope.launch {
            readingState = ReadingState(mode, true)
            repeat(count) {
                LogJournal.log(LOGGER_NAME, "${it + 1}")
                delay(Duration.ofSeconds(1).toMillis())
            }
            readingState?.isReadingNow = false
            println("Форд закончил cчитать")
            LogJournal.log(LOGGER_NAME, "Finish reading")
        }
        return SpeakingAction(
            sourceName = ACTION_SOURCE_NAME,
            job = job,
            type = SpeakingActionType.COUNT,
            mode = mode,
        )
    }

    fun isStillReading() = readingState?.isReadingNow ?: false
}
