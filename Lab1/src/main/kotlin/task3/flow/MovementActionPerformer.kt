package task3.flow

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import task3.log.Logger
import task3.model.MovementAction
import task3.model.SpeakingAction
import java.time.Duration

class MovementActionPerformer {
    companion object {
        const val LOGGER_NAME = "MOVEMENT_ACTION_PERFORMER"
    }

    fun performMovementActionSaying(
        movementAction: MovementAction,
        times: Int,
        speakingAction: SpeakingAction,
        text: String,
    ): Job {
        val (ch, rate) = speakingAction.toCharacterizationWithAggressiveRate(movementAction)
        val aggressiveRate = mapToAggressiveRate(rate)
        return performMovementActionRepeating(
            movementAction = movementAction,
            times = times,
        ) {
            Logger.log(LOGGER_NAME, "Performing $ch speaking action with aggressiveRate=$aggressiveRate")
            speakingAction.perform(text)
        }
    }

    private fun performMovementActionRepeating(
        movementAction: MovementAction,
        times: Int,
        actionToRepeat: () -> Unit,
    ): Job {
        val fromName = movementAction.from.getName()
        val toName = movementAction.to.getName()
        val mode = movementAction.mode

        val job = GlobalScope.launch {
            Logger.log(LOGGER_NAME, "$fromName start movement action to $toName $mode")

            repeat(times) {
                Logger.log(LOGGER_NAME, "$fromName performing moving action $mode to $toName: $it")
                movementAction.perform()
                actionToRepeat()
                delay(Duration.ofSeconds(1).toMillis())
            }

            Logger.log(LOGGER_NAME, "$fromName finish movement action to $toName $mode")
        }

        return job
    }
}