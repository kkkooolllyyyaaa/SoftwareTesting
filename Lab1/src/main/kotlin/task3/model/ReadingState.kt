package task3.model

import task3.enum.ReadMode

data class ReadingState(
    val mode: ReadMode,
    var isReadingNow: Boolean,
)
