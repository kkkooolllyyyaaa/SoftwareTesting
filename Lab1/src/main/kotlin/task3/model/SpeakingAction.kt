package task3.model

import kotlinx.coroutines.Job
import task3.enum.SpeakingActionCharacterization
import task3.enum.SpeakingActionMode
import task3.enum.SpeakingActionType

data class SpeakingAction(
    val sourceName: String,
    val mode: SpeakingActionMode,
    val type: SpeakingActionType,
    val job: Job?,
)
