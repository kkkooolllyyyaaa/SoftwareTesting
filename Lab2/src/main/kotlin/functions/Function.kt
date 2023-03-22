package functions

import functions.base.NaturalLogarithm
import functions.nonbase.Cosine
import functions.nonbase.Logarithm
import functions.nonbase.Secant
import java.math.BigDecimal

class Function(
    private val naturalLogarithm: NaturalLogarithm,
    private val cosine: Cosine,
    private val secant: Secant,
) : MathFunction {
    private var log2 = Logarithm(naturalLogarithm, BigDecimal(2))
    private var log3 = Logarithm(naturalLogarithm, BigDecimal(3))
    private var log5 = Logarithm(naturalLogarithm, BigDecimal(5))
    private var log10 = Logarithm(naturalLogarithm, BigDecimal(10))

    override fun value(x: BigDecimal): BigDecimal {
        return if (x > BigDecimal(0)) {
            ((((log10.value(x) / log3.value(BigDecimal(3))) * (log5.value(x) / log2.value(x))) / log10.value(
                x
            )) * ((log5.value(x) * naturalLogarithm.value(x)) * (naturalLogarithm.value(x) * log3.value(x)))).pow(3)
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
            )) * ((log5.valueDecomposed(x, eps) * naturalLogarithm.valueDecomposed(
                x,
                eps
            )) * (naturalLogarithm.valueDecomposed(x, eps) * log3.valueDecomposed(x, eps)))).pow(3)
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
            )) * ((log5.valueDecomposed(x, n) * naturalLogarithm.valueDecomposed(
                x,
                n
            )) * (naturalLogarithm.valueDecomposed(x, n) * log3.valueDecomposed(x, n)))).pow(3)
        } else {
            cosine.valueDecomposed(x, n) + secant.valueDecomposed(x, n)
        }
    }
}
