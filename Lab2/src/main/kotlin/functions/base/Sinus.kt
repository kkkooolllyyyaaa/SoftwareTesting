package functions.base

import functions.MathFunction
import solution.tables.Factorial
import java.math.BigDecimal
import java.math.RoundingMode
import kotlin.math.sin

class Sinus(
    private val factorial: Factorial,
    private val scale: Int = 5,
) : MathFunction {
    override fun value(x: BigDecimal): BigDecimal {
        return sin(x.toDouble()).toBigDecimal()
    }

    override fun valueDecomposed(x: BigDecimal, eps: BigDecimal): BigDecimal {
        var result = BigDecimal.ZERO
        var sign = BigDecimal.ONE
        val xx = x * x
        var pw = x
        var fti = BigDecimal.ONE

        var i = 1
        while (!(value(x) - eps <= result.abs() && result.abs() <= value(x) + eps)) {
            val twoI = (2 * i).toBigDecimal()
            fti = fti.divide(twoI, scale, RoundingMode.UP)
            result += sign * pw * fti
            fti = fti.divide(twoI + BigDecimal.ONE)
            sign = -sign
            pw *= xx
            i++
        }
        return result
    }

    override fun valueDecomposed(x: BigDecimal, n: Int): BigDecimal {
        var result = BigDecimal.ZERO
        var sign = BigDecimal.ONE
        val xx = x * x
        var pw = x
        var fti = BigDecimal.ONE
        for (i in 1..n) {
            val twoI = (2 * i).toBigDecimal()
            fti = fti.divide(twoI, scale, RoundingMode.UP)
            result += sign * pw * fti
            fti = fti.divide(twoI + BigDecimal.ONE)
            sign = -sign
            pw *= xx
        }
        return result
    }

    private fun decomposedStep(x: BigDecimal, step: Int): BigDecimal {
        val m1 = if (step % 2 == 1) { // (-1)^(n + 1)
            BigDecimal.ONE
        } else {
            -BigDecimal.ONE
        }
        val m2 = x.pow(2 * step + 1)
        val d1 = factorial.factorial(2 * step + 1)

        return (m1 * m2).divide(d1, scale, RoundingMode.UP)
    }
}