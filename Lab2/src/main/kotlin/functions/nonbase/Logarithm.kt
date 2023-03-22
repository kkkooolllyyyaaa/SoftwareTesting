package functions.nonbase

import exception.InputValidationException
import functions.MathFunction
import functions.base.NaturalLogarithm
import java.math.BigDecimal

class Logarithm(private val naturalLogarithm: NaturalLogarithm, val base: BigDecimal = BigDecimal(3)) :
    MathFunction {
    override fun value(x: BigDecimal): BigDecimal {
        if (x < BigDecimal.ZERO) {
            return BigDecimal.valueOf(Double.NaN)
        }
        return naturalLogarithm.value(x) / naturalLogarithm.value(base)
    }

    override fun valueDecomposed(x: BigDecimal, eps: BigDecimal): BigDecimal {
        if (x < BigDecimal.ZERO) {
            throw InputValidationException("the value of the logarithm cannot be less than zero")
        }
        return naturalLogarithm.valueDecomposed(x, eps) / naturalLogarithm.valueDecomposed(base, eps)
    }

    override fun valueDecomposed(x: BigDecimal, n: Int): BigDecimal {
        if (x < BigDecimal.ZERO) {
            throw InputValidationException("the value of the logarithm cannot be less than zero")
        }
        return naturalLogarithm.valueDecomposed(x, n) / naturalLogarithm.valueDecomposed(base, n)
    }

}