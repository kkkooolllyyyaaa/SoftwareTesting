package functions

import java.math.BigDecimal

class MockFunction(
    private val valueSource: Map<BigDecimal, BigDecimal>
) : MathFunction {
    override fun value(x: BigDecimal): BigDecimal {
        return valueSource[x] ?: throw RuntimeException("Undefined x value")
    }

    override fun valueDecomposed(x: BigDecimal, eps: BigDecimal): BigDecimal {
        return valueSource[x] ?: throw RuntimeException("Undefined x value")
    }

    override fun valueDecomposed(x: BigDecimal, n: Int): BigDecimal {
        return valueSource[x] ?: throw RuntimeException("Undefined x value")
    }
}
