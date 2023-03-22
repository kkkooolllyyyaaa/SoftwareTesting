package functions.nonbase

import Constants
import assertXNotZero
import functions.MathFunction
import java.math.BigDecimal
import java.math.RoundingMode

class Secant(
    private val cosine: Cosine,
) : MathFunction {
    override fun value(x: BigDecimal): BigDecimal {
        val cosValue = cosine.value(x)
        assertXNotZero(cosValue)

        return 1.0.toBigDecimal().divide(cosine.value(x), Constants.SCALE, RoundingMode.HALF_UP)
    }

    override fun
        valueDecomposed(x: BigDecimal, eps: BigDecimal): BigDecimal {
        val cosValue = cosine.valueDecomposed(x, eps)
        assertXNotZero(cosValue)

        return BigDecimal.ONE
            .divide(cosine.valueDecomposed(x, eps), eps.scale(), RoundingMode.HALF_UP)
    }

    override fun valueDecomposed(x: BigDecimal, n: Int): BigDecimal {
        return BigDecimal.ONE
            .divide(cosine.valueDecomposed(x, n), Constants.SCALE, RoundingMode.HALF_UP)
    }
}
