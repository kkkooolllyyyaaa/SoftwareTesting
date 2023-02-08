package task3.model

import kotlinx.coroutines.Job
import task3.enum.SpeakingActionMode
import task3.enum.SpeakingActionType
import task3.log.Logger

data class SpeakingAction(
    val sourceName: String,
    val mode: SpeakingActionMode,
    val type: SpeakingActionType,
    var job: Job? = null,
) {
    fun perform(text: String): String {
        val toLog = "$sourceName $type $mode `$text`"
        Logger.log(
            logger = sourceName,
            text = toLog,
        )
        return toLog
    }
}
