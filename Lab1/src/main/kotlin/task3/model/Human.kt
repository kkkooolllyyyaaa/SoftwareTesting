package task3.model

class Human(
    private val name: String? = null,
) {
    fun getName(): String {
        return name ?: "Unknown"
    }
}
