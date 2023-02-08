package task3.flow

import exceptions.AggressiveRateValidationException
import task3.enum.*
import task3.model.MovementAction
import task3.model.SpeakingAction

fun mapToAggressiveRate(rate: Double) =
    when (rate) {
        in 0.0..0.25 -> AggressiveRate.SMALL
        in 0.25..0.50 -> AggressiveRate.MEDIUM
        in 0.50..0.75 -> AggressiveRate.HIGH
        in 0.75..1.0 -> AggressiveRate.ONE_OF_THE_MOST
        else -> throw AggressiveRateValidationException()
    }

fun SpeakingAction.toCharacterizationWithAggressiveRate(movementAction: MovementAction): Pair<SpeakingActionCharacterization, Double> {
    return if (this.type == SpeakingActionType.SAY &&
        this.mode == SpeakingActionMode.ALOUD &&
        movementAction.mode == MoveMode.SLOWLY
    ) {
        SpeakingActionCharacterization.AGGRESSIVE to 0.99
    } else {
        SpeakingActionCharacterization.NONE to 0.0
    }
}