package functions

import java.math.BigDecimal

interface MathFunction {
    fun value(x: BigDecimal): BigDecimal

    fun valueDecomposed(x: BigDecimal, eps: BigDecimal = 0.0000000000000001.toBigDecimal()): BigDecimal

    fun valueDecomposed(x: BigDecimal, n: Int): BigDecimal
}
