package task2

import kotlin.math.abs

data class ListNode(
    var key: Int,
    var value: Int,
    var next: ListNode? = null,
)

class OurBucketSortMap(
    private val capacity: Int,
) {
    private val buckets: Array<ListNode?> = arrayOfNulls(capacity)

    private fun calculateIndex(key: Int): Int {
        return abs(key.hashCode()) % capacity
    }

    fun put(key: Int, value: Int) {
        val index = calculateIndex(key)
        if (buckets[index] == null) {
            buckets[index] = ListNode(key, value)
            return
        }

        var node = buckets[index]
        var prev = buckets[index]
        while (node != null && node.key.hashCode() < key.hashCode()) {
            prev = node
            node = node.next
        }

        if (node === prev) {
            buckets[index] = ListNode(key, value)
            buckets[index]!!.next = node
            return
        }

        if (node!!.key.hashCode() == key.hashCode()) {
            node.value = value
            return
        }

        val next = node
        prev!!.next = ListNode(key, value)
        prev.next!!.next = next
    }

    fun get(key: Int): Int? {
        val index = calculateIndex(key)
        if (buckets[index] == null) {
            return null
        }
        val found: ListNode? = find(key)
        return found?.value
    }

    fun getSortedFull(): List<Pair<Int, Int>> {
        val answer = mutableListOf<Pair<Int, Int>>()
        buckets.forEach {
            var node = it
            while (node != null) {
                answer.add(node.key to node.value)
                node = node.next
            }
        }
        return answer
    }

    fun getSortedKeys(): List<Int> {
        val answer = mutableListOf<Int>()
        buckets.forEach {
            var node = it
            while (node != null) {
                answer.add(node.key)
                node = node.next
            }
        }
        return answer
    }

    private fun find(key: Int): ListNode? {
        val index = calculateIndex(key)
        var node = buckets[index]
        while (node != null && node.key != key) {
            node = node.next
        }

        return node
    }
}
