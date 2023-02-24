package solution.tables

import java.math.BigDecimal

class Factorial {
    private val cache = ArrayList<BigDecimal>(10_000)

    init {
        var i = 0
        var cur = BigDecimal.ONE
        IntRange(0, 10_000).forEach {
            cache.add(cur)
            i++
            cur *= i.toBigDecimal()
        }
    }

    fun factorial(n: Int): BigDecimal {
        if (cache.size < n) {
            return n.toBigDecimal() * factorial(n - 1)
        }
        return cache[n]
    }
}
