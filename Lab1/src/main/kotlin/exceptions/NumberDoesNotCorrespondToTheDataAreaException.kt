package exceptions

import java.lang.RuntimeException

class NumberDoesNotCorrespondToTheDataAreaException : RuntimeException() {
    init {
        super.message
    }
}