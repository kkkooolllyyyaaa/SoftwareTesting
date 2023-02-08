package task2

class ListNode {
    var key = 0
    var `val`: Int
    var next: ListNode? = null

    constructor(`val`: Int) {
        this.`val` = `val`
    }

    constructor(key: Int, `val`: Int) {
        this.key = key
        this.`val` = `val`
    }
}

class OurHashMap {
    public lateinit var buckets: Array<ListNode?>
    private var capacity = 10000

    fun MyHashMap() {
        buckets = arrayOfNulls(capacity)
    }

    private fun hashFunc(key: Int): Int {
        return key % capacity
    }

    fun put(key: Int, value: Int) {
        val hash = hashFunc(key)
        if (buckets[hash] == null) {
            buckets[hash] = ListNode(-1, -1)
        }
        val prev: ListNode? = find(buckets[hash], key)
        if (prev!!.next == null) {
            prev.next = ListNode(key, value)
        } else prev.next!!.`val` = value
    }


    private fun find(bucket: ListNode?, key: Int): ListNode? {
        var node: ListNode? = bucket
        var prev: ListNode? = null
        while (node != null && node.key !== key) {
            prev = node
            node = node.next
        }
        return prev
    }

    operator fun get(key: Int): Int {
        val hash = hashFunc(key)
        if (buckets[hash] == null) {
            return -1
        }
        val node: ListNode? = find(buckets[hash], key)
        return if (node!!.next == null) -1 else node.next!!.`val`

    }

    fun remove(key: Int) {
        val hash = hashFunc(key)
        if (buckets[hash] != null) {
            val prev: ListNode? = find(buckets[hash], key)
            if (prev!!.next != null) {
                prev.next = prev.next!!.next
            }

        }
    }
}