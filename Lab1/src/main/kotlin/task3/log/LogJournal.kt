package task3.log

class LogJournal private constructor() {
    private val logsList = mutableListOf<Pair<String, String>>()

    companion object {
        private var instance: LogJournal? = null

        fun getInstance(): LogJournal {
            if (instance == null) {
                instance = LogJournal()
            }
            return instance!!
        }

        fun log(logger: String, text: String) {
            getInstance().logsList.add(logger to text)
        }

        fun getLogs(): String {
            return getInstance().logsList.joinToString(separator = "\n") { "${it.first}: ${it.second}" }
        }
    }
}
