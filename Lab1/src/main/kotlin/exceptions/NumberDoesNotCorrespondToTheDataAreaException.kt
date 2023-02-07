package exceptions

import java.lang.RuntimeException

class NumberDoesNotCorrespondToTheDataAreaException(message: String) : RuntimeException() {
    init {
        super.message
    }
}