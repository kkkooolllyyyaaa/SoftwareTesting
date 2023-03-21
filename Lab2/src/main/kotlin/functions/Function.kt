package functions

import functions.base.NaturalLogarithm
import functions.nonbase.Cosine
import functions.nonbase.Logarithm
import java.math.BigDecimal

class Function(private val naturalLogarithm: NaturalLogarithm, private val cosine: Cosine) : MathFunction {
    private var log_2 = Logarithm(naturalLogarithm, BigDecimal(2))
    private var log_3 = Logarithm(naturalLogarithm, BigDecimal(3))
    private var log_5 = Logarithm(naturalLogarithm, BigDecimal(5))
    private var log_10 = Logarithm(naturalLogarithm, BigDecimal(10))

    override fun value(x: BigDecimal): BigDecimal {
        return if (x > BigDecimal(0)) {
            ((((log_10.value(x) / log_3.value(BigDecimal(3))) * (log_5.value(x) / log_2.value(x))) / log_10.value(
                x
            )) * ((log_5.value(x) * naturalLogarithm.value(x)) * (naturalLogarithm.value(x) * log_3.value(x)))).pow(3)
        } else {
            //TODO sec(x)
            cosine.value(x) + BigDecimal(0)
        }
    }

    override fun valueDecomposed(x: BigDecimal, eps: BigDecimal): BigDecimal {
        return if (x > BigDecimal(0)) {
            ((((log_10.valueDecomposed(x, eps) / log_3.valueDecomposed(BigDecimal(3), eps)) * (log_5.valueDecomposed(
                x,
                eps
            ) / log_2.valueDecomposed(x, eps))) / log_10.valueDecomposed(
                x, eps
            )) * ((log_5.valueDecomposed(x, eps) * naturalLogarithm.valueDecomposed(
                x,
                eps
            )) * (naturalLogarithm.valueDecomposed(x, eps) * log_3.valueDecomposed(x, eps)))).pow(3)
        } else {
            //TODO sec(x)
            cosine.valueDecomposed(x, eps) + BigDecimal(0)
        }
    }

    override fun valueDecomposed(x: BigDecimal, n: Int): BigDecimal {
        return if (x > BigDecimal(0)) {
            ((((log_10.valueDecomposed(x, n) / log_3.valueDecomposed(BigDecimal(3), n)) * (log_5.valueDecomposed(
                x,
                n
            ) / log_2.valueDecomposed(x, n))) / log_10.valueDecomposed(
                x, n
            )) * ((log_5.valueDecomposed(x, n) * naturalLogarithm.valueDecomposed(
                x,
                n
            )) * (naturalLogarithm.valueDecomposed(x, n) * log_3.valueDecomposed(x, n)))).pow(3)
        } else {
            //TODO sec(x)
            cosine.valueDecomposed(x, n) + BigDecimal(0)
        }
    }


}