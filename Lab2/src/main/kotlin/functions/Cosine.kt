package functions

import Constants
import functions.base.Sinus
import java.math.BigDecimal
import kotlin.math.cos

class Cosine(
    private val sin: Sinus
) : MathFunction {
    override fun value(x: BigDecimal): BigDecimal {
        return cos(x.toDouble()).toBigDecimal()
    }

    override fun valueDecomposed(x: BigDecimal, eps: BigDecimal): BigDecimal {
        return sin.valueDecomposed(
            x = Constants.Numbers.PI_DIV_2 - x,
            eps = eps,
        )
    }

    override fun valueDecomposed(x: BigDecimal, n: Int): BigDecimal {
        return sin.valueDecomposed(
            x = Constants.Numbers.PI_DIV_2 - x,
            n = n,
        )
    }
}
