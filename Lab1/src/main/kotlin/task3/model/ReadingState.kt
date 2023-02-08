package task3.model

import task3.enum.SpeakingActionMode

data class ReadingState(
    val mode: SpeakingActionMode,
    var isReadingNow: Boolean,
)
