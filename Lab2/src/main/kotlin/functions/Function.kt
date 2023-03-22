package functions

import java.math.BigDecimal

class Function(
    private val ln: MathFunction,
    private val cosine: MathFunction,
    private val secant: MathFunction,
    private var log2: MathFunction,
    private var log3: MathFunction,
    private var log5: MathFunction,
    private var log10: MathFunction,
) : MathFunction {

    override fun value(x: BigDecimal): BigDecimal {
        return if (x > BigDecimal(0)) {
            ((((log10.value(x) / log3.value(BigDecimal(3))) * (log5.value(x) / log2.value(x))) / log10.value(
                x
            )) * ((log5.value(x) * ln.value(x)) * (ln.value(x) * log3.value(x)))).pow(3)
        } else {
            cosine.value(x) + secant.value(x)
        }
    }

    override fun valueDecomposed(x: BigDecimal, eps: BigDecimal): BigDecimal {
        return if (x > BigDecimal(0)) {
            ((((log10.valueDecomposed(x, eps) / log3.valueDecomposed(BigDecimal(3), eps)) * (log5.valueDecomposed(
                x,
                eps
            ) / log2.valueDecomposed(x, eps))) / log10.valueDecomposed(
                x, eps
            )) * ((log5.valueDecomposed(x, eps) * ln.valueDecomposed(
                x,
                eps
            )) * (ln.valueDecomposed(x, eps) * log3.valueDecomposed(x, eps)))).pow(3)
        } else {
            cosine.valueDecomposed(x, eps) + secant.valueDecomposed(x, eps)
        }
    }

    override fun valueDecomposed(x: BigDecimal, n: Int): BigDecimal {
        return if (x > BigDecimal(0)) {
            ((((log10.valueDecomposed(x, n) / log3.valueDecomposed(BigDecimal(3), n)) * (log5.valueDecomposed(
                x,
                n
            ) / log2.valueDecomposed(x, n))) / log10.valueDecomposed(
                x, n
            )) * ((log5.valueDecomposed(x, n) * ln.valueDecomposed(
                x,
                n
            )) * (ln.valueDecomposed(x, n) * log3.valueDecomposed(x, n)))).pow(3)
        } else {
            cosine.valueDecomposed(x, n) + secant.valueDecomposed(x, n)
        }
    }
}
