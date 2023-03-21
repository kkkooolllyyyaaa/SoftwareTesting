package functions.nonbase

import Constants
import functions.MathFunction
import java.math.BigDecimal
import java.math.RoundingMode

class Secant(
    private val cosine: Cosine,
) : MathFunction {
    override fun value(x: BigDecimal): BigDecimal {
        return 1.0.toBigDecimal().divide(cosine.value(x))
    }

    override fun valueDecomposed(x: BigDecimal, eps: BigDecimal): BigDecimal {
        return BigDecimal.ONE
            .divide(cosine.valueDecomposed(x, eps), eps.scale(), RoundingMode.HALF_UP)
    }

    override fun valueDecomposed(x: BigDecimal, n: Int): BigDecimal {
        return BigDecimal.ONE
            .divide(cosine.valueDecomposed(x, n), Constants.SCALE, RoundingMode.HALF_UP)
    }
}
