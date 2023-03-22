package functions.nonbase

import functions.MathFunction
import functions.base.NaturalLogarithm
import java.math.BigDecimal
import java.math.RoundingMode

class Logarithm(private val naturalLogarithm: NaturalLogarithm, private val base: BigDecimal = BigDecimal(3)) :
    MathFunction {
    override fun value(x: BigDecimal): BigDecimal {
        return naturalLogarithm.value(x) / naturalLogarithm.value(base)
    }

    override fun valueDecomposed(x: BigDecimal, eps: BigDecimal): BigDecimal {
        return naturalLogarithm.valueDecomposed(x, eps).divide(naturalLogarithm.valueDecomposed(base, eps),30, RoundingMode.UP)
    }

    override fun valueDecomposed(x: BigDecimal, n: Int): BigDecimal {
        return naturalLogarithm.valueDecomposed(x, n).divide(naturalLogarithm.valueDecomposed(base, n), 30, RoundingMode.UP)
    }

}