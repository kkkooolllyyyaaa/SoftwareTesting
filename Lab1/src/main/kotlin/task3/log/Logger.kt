package task3.log

import org.jetbrains.annotations.TestOnly

class Logger private constructor() {
    private val logsList = mutableListOf<Pair<String, String>>()

    companion object {
        private var instance: Logger? = null

        fun getInstance(): Logger {
            if (instance == null) {
                instance = Logger()
            }
            return instance!!
        }

        fun log(logger: String, text: String) {
            getInstance().logsList.add(logger to text)
        }

        fun getLogs(): String {
            return getInstance().logsList.joinToString(separator = "\n") { "${it.first}: ${it.second}" }
        }

        @TestOnly
        fun clear() {
            getInstance().logsList.clear()
        }
    }
}
