package task3

import task3.enum.ReadMode
import task3.model.Ford
import java.time.Duration

suspend fun main(args: Array<String>) {
    val ford = Ford.getInstance()
    val readJob = ford.startReading(ReadMode.ALOUD, Duration.ofSeconds(4L))
    readJob.join()
}
