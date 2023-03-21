package functions.base

import Constants
import functions.MathFunction
import java.math.BigDecimal
import java.math.RoundingMode
import kotlin.math.abs
import kotlin.math.cos
import kotlin.math.floor

class Cosine : MathFunction {
    override fun value(x: BigDecimal): BigDecimal {
        return cos(x.toDouble()).toBigDecimal()
    }

    override fun valueDecomposed(x: BigDecimal, eps: BigDecimal): BigDecimal {
        var thetaNorm = x.abs()
        thetaNorm -= thetaNorm.divide(Constants.Numbers.PI.divide(Constants.Numbers.TWO), RoundingMode.UP)
        var result = BigDecimal.ONE
        var step = BigDecimal.ONE
        var i = 1
        while (step > eps && i != Int.MAX_VALUE) {
            val iBigDecimal = i.toBigDecimal()
            step = (step * thetaNorm * thetaNorm)
                .divide(Constants.Numbers.TWO * iBigDecimal - BigDecimal.ONE, RoundingMode.UP)
                .divide(Constants.Numbers.TWO * iBigDecimal, RoundingMode.UP)

            if (i % 2 == 1) {
                result -= step
            } else {
                result += step
            }
            i++
        }
        return result
    }

    override fun valueDecomposed(x: BigDecimal, n: Int): BigDecimal {
        throw NotImplementedError()
    }

    fun calcCos(theta: Double, accuracy: Double): Double {
        var thetaNorm = abs(theta)
        thetaNorm -= floor(thetaNorm / Math.PI / 2) * 2 * Math.PI
        var result = 1.0
        var step = 1.0
        var i = 1
        while (step > accuracy && i != Int.MAX_VALUE) {
            step = step * thetaNorm * thetaNorm / (2 * i - 1) / (2 * i)
            if (i % 2 == 1) {
                result -= step
            } else {
                result += step
            }
            i++
        }
        return result
    }
}
