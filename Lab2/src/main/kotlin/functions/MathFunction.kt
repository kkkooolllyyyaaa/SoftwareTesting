package functions

import java.math.BigDecimal

interface MathFunction {
    fun value(x: BigDecimal): BigDecimal

    fun valueDecomposed(x: BigDecimal, eps: BigDecimal): BigDecimal

    fun valueDecomposed(x: BigDecimal, n: Int): BigDecimal
}
