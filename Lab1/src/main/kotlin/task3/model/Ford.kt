package task3.model

import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import task3.enum.ReadMode
import java.time.Duration

class Ford private constructor() {
    private var readingState: ReadingState? = null

    companion object {
        private var instance: Ford? = null
        fun getInstance(): Ford {
            if (instance == null) {
                instance = Ford()
            }
            return instance!!
        }
    }

    fun startReading(mode: ReadMode, duration: Duration): Job {
        println("Форд начал читать ${mode.humanReadableRu} на протяжении ${duration.seconds} секунд")
        val job = runBlocking {
            return@runBlocking launch {
                readingState = ReadingState(mode, true)
                delay(duration.toMillis())
                readingState?.isReadingNow = false
                println("Форд закончил читать")
            }
        }
        return job
    }

    fun isStillReading() = readingState?.isReadingNow ?: false
}
