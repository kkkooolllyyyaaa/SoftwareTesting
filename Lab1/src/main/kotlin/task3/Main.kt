package task3

import task3.log.LogJournal
import task3.model.Computer
import task3.model.Ford

suspend fun main(args: Array<String>) {
    val ford = Ford.getInstance()
    val action = ford.startCountingAloud(4)
    Computer.getInstance().applyAction(action)
    action.job?.join()
    println(LogJournal.getLogs())
}
