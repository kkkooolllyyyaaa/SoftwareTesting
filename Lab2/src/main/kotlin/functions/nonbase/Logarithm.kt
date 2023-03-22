package functions.nonbase

import functions.MathFunction
import functions.base.NaturalLogarithm
import java.math.BigDecimal

class Logarithm(private val naturalLogarithm: NaturalLogarithm, val base: BigDecimal = BigDecimal(3)) :
    MathFunction {
    override fun value(x: BigDecimal): BigDecimal {
        return naturalLogarithm.value(x) / naturalLogarithm.value(base)
    }

    override fun valueDecomposed(x: BigDecimal, eps: BigDecimal): BigDecimal {
        return naturalLogarithm.valueDecomposed(x, eps) / naturalLogarithm.valueDecomposed(base, eps)
    }

    override fun valueDecomposed(x: BigDecimal, n: Int): BigDecimal {
        return naturalLogarithm.valueDecomposed(x, n) / naturalLogarithm.valueDecomposed(base, n)
    }

}