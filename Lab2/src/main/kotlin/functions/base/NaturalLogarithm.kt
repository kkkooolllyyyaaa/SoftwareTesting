package functions.base

import Constants
import exception.InputValidationException
import functions.MathFunction
import java.math.BigDecimal
import java.math.RoundingMode
import kotlin.math.ln

class NaturalLogarithm(private val scale: Int = 5) : MathFunction {
    override fun valueDecomposed(x: BigDecimal, eps: BigDecimal): BigDecimal {
        assertX(x)

        return calculateDecomposition(
            x = x,
            n = null,
            eps = eps,
        )
    }

    override fun valueDecomposed(x: BigDecimal, n: Int): BigDecimal {
        assertX(x)
        return calculateDecomposition(
            x = x,
            n = n,
            eps = null,
        )
    }

    override fun value(x: BigDecimal): BigDecimal {
        assertX(x)
        return ln(x.toDouble()).toBigDecimal()
    }

    private fun calculateDecomposition(
        x: BigDecimal,
        n: Int?,
        eps: BigDecimal?,
    ): BigDecimal {
        if (n == null && eps == null) {
            return value(x)
        }
        assertX(x)

        val constant = ((x - BigDecimal.ONE).pow(2))
            .divide((x + BigDecimal.ONE).pow(2), scale, RoundingMode.UP)
        var currentValue = (x - BigDecimal.ONE)
            .divide((x + BigDecimal.ONE), scale, RoundingMode.UP)

        var sum = BigDecimal.ZERO
        var step = 1

        fun condition(): Boolean {
            return if (n != null) {
                step <= n
            } else if (eps != null) {
                currentValue.abs() > eps.divide(Constants.Numbers.TWO, scale, RoundingMode.UP)
            } else {
                false
            }
        }

        while (condition()) {
            sum += currentValue
            val nVal = (2 * step - 1).toBigDecimal()
            currentValue = (nVal * currentValue * constant) / (2 * step + 1).toBigDecimal()
            step++
        }
        sum *= Constants.Numbers.TWO
        return sum
    }

    private fun assertX(x: BigDecimal) {
        if (x <= BigDecimal.ZERO) {
            throw InputValidationException("Логарифм от отрицательного числа")
        }
    }
}
