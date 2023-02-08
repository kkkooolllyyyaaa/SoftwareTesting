package task3.model

import task3.enum.MoveMode
import task3.log.Logger

data class MovementAction(
    val from: Human = Human(),
    val to: Human = Human(),
    val mode: MoveMode = MoveMode.NORMAL,
) {
    fun perform() {
        Logger.log(
            logger = from.getName(),
            text = "${from.getName()} $mode moving to ${to.getName()}",
        )
    }
}