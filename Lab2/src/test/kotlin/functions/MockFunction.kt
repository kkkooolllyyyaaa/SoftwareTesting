package functions

import java.math.BigDecimal

class MockFunction(
    private val mock: MathFunction
) : MathFunction {
    override fun value(x: BigDecimal): BigDecimal {
        return mock.value(x)
    }

    override fun valueDecomposed(x: BigDecimal, eps: BigDecimal): BigDecimal {
        return mock.value(x)
    }

    override fun valueDecomposed(x: BigDecimal, n: Int): BigDecimal {
        return mock.value(x)
    }
}
