package functions

import functions.base.NaturalLogarithm
import functions.base.Sinus
import functions.nonbase.Cosine
import functions.nonbase.Logarithm
import functions.nonbase.Secant
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import java.math.BigDecimal

class FunctionTestXPositive {
    private val ln = NaturalLogarithm()
    private val sinus = Sinus()
    private val cosine = Cosine(sinus)
    private val secant = Secant(cosine)
    private var log2 = Logarithm(ln, BigDecimal(2))
    private var log3 = Logarithm(ln, BigDecimal(3))
    private var log5 = Logarithm(ln, BigDecimal(5))
    private var log10 = Logarithm(ln, BigDecimal(10))

    private val function = Function(
        ln = ln,
        cosine = cosine,
        secant = secant,
        log2 = log2,
        log3 = log3,
        log5 = log5,
        log10 = log10,
    )

    @Test
    fun `ODZ x = 1`() {
        assertThrows<ArithmeticException> {
            function.value(BigDecimal.ONE)
        }
    }
}
