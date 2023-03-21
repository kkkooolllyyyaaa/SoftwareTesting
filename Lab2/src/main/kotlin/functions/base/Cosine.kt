package functions.base

import Constants
import functions.MathFunction
import java.math.BigDecimal
import java.math.RoundingMode
import kotlin.math.cos

class Cosine(private val scale: Int = 5) : MathFunction {
    override fun value(x: BigDecimal): BigDecimal {
        return cos(x.toDouble()).toBigDecimal()
    }

    override fun valueDecomposed(x: BigDecimal, eps: BigDecimal): BigDecimal {
        var thetaNorm = x.abs()
        thetaNorm -= thetaNorm.divide(Constants.Numbers.PI.divide(Constants.Numbers.TWO), scale, RoundingMode.UP)
        var result = BigDecimal.ONE
        var step = BigDecimal.ONE
        var i = 1
        while (step > eps && i != Int.MAX_VALUE) {
            val iBigDecimal = i.toBigDecimal()
            step = (step * thetaNorm * thetaNorm)
                .divide(Constants.Numbers.TWO * iBigDecimal - BigDecimal.ONE, scale, RoundingMode.UP)
                .divide(Constants.Numbers.TWO * iBigDecimal, scale, RoundingMode.UP)

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
}
