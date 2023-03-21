package functions.base

import functions.MathFunction
import java.math.BigDecimal
import java.math.RoundingMode
import kotlin.math.sin

class Sinus : MathFunction {
    override fun value(x: BigDecimal): BigDecimal {
        return sin(x.toDouble()).toBigDecimal()
    }

    override fun valueDecomposed(x: BigDecimal, eps: BigDecimal): BigDecimal {
        val pi2 = 2 * Math.PI
        var xDouble = x.toDouble()

        while (xDouble < pi2) {
            xDouble += pi2;
        }
        while (xDouble > pi2) {
            xDouble -= pi2
        }

        var i = 0
        var sum = BigDecimal.ZERO
        var prev: BigDecimal

        do {
            prev = sum
            val allProd = prod(xDouble, 2 * i + 1)
            sum += minusOnePow(i).multiply(allProd)
            i++
        } while (0.1.toBigDecimal().pow(eps.scale()) < prev.subtract(sum).abs())
        return sum.setScale(eps.scale(), RoundingMode.HALF_UP)
    }

    override fun valueDecomposed(x: BigDecimal, n: Int): BigDecimal {
        val pi2 = 2 * Math.PI
        var xDouble = x.toDouble()

        while (xDouble > pi2) {
            xDouble -= pi2
        }
        while (xDouble < -pi2) {
            xDouble += pi2
        }

        var i = 0
        var sum = BigDecimal.ZERO

        do {
            val allProd = prod(xDouble, 2 * i + 1)
            sum += minusOnePow(i).multiply(allProd)
            i++
        } while (i < n)
        return sum
    }

    private fun minusOnePow(n: Int): BigDecimal {
        return BigDecimal.valueOf((1 - n % 2 * 2).toLong())
    }

    private fun prod(x: Double, n: Int): BigDecimal {
        var accum = BigDecimal(1)
        for (i in 1..n) {
            accum = accum.multiply(BigDecimal(x / i))
        }
        return accum
    }
}
