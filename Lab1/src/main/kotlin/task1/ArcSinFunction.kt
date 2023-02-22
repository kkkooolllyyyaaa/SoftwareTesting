package task1

import exceptions.NumberDoesNotCorrespondToTheDataAreaException
import java.math.BigDecimal

class ArcSinFunction {
    private var factorialCache = hashMapOf<BigDecimal, BigDecimal>()

    fun calculate(x: Double, n: Int): Double {
        return calculate(x.toBigDecimal(), n).toDouble()
    }

    private fun calculate(x: BigDecimal, N: Int): BigDecimal {
        if (x < -BigDecimal.ONE || x > BigDecimal.ONE) {
            throw NumberDoesNotCorrespondToTheDataAreaException()
        }
        if (N == 0) {
            return x
        }

        var res = BigDecimal.ZERO
        for (i in 0..N) {
            val n1 = factorial((2 * i).toBigDecimal()) // 2n!
            val n2 = x.pow(2 * i + 1)

            val m1 = BigDecimal.valueOf(4L).pow(i) // 4^n
            val m2 = factorial(i.toBigDecimal()).pow(2) // (n!)^2
            val m3 = (2 * i + 1).toBigDecimal() // 2n + 1
            res += (n1 * n2) / (m1 * m2 * m3)
        }
        return res
    }

    private fun factorial(number: BigDecimal): BigDecimal {
        if (!factorialCache.containsKey(number)) {
            factorialCache[number] = if (number <= BigDecimal.ONE) {
                BigDecimal.ONE
            } else {
                number * factorial(number - BigDecimal.ONE)
            }
        }
        return factorialCache[number]!!
    }
}