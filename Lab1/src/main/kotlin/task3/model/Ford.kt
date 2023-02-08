package task3.model

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import task3.log.Logger
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

    suspend fun performSpeakingActionManyTimes(
        speakingAction: SpeakingAction,
        times: Int,
    ): SpeakingAction {
        Logger.log(LOGGER_NAME, "Start counting for $times seconds")

        val job = GlobalScope.launch {
            readingState = ReadingState(speakingAction.mode, true)
            repeat(times) {
                speakingAction.perform((it + 1).toString())
                delay(Duration.ofSeconds(1).toMillis())
            }
            readingState?.isReadingNow = false
            Logger.log(LOGGER_NAME, "Finish reading")
        }
        speakingAction.job = job
        return speakingAction
    }

    fun isStillReading() = readingState?.isReadingNow ?: false
}
