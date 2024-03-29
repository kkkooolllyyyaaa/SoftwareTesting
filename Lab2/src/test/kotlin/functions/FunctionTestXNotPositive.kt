package functions

import Constants
import functions.base.NaturalLogarithm
import functions.base.Sinus
import functions.nonbase.Cosine
import functions.nonbase.Logarithm
import functions.nonbase.Secant
import org.junit.jupiter.api.Order
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import util.assertAlmostEqual
import java.math.BigDecimal
import kotlin.random.Random

class FunctionTestXNotPositive {
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
    @Order(1)
    fun `ODZ x = -pi div 2`() {
        // x = -pi/2 - 2pk, k in N
        var current = -Constants.Numbers.PI_DIV_2
        IntRange(0, 20).forEach {
            assertThrows<ArithmeticException> {
                function.valueDecomposed(current)
            }
            current -= Constants.Numbers.PI_2
        }
    }

    @Test
    @Order(2)
    fun `mock sec test`() {
        val fn = Function(
            ln = ln,
            cosine = cosine,
            secant = MockFunction(secant),
            log2 = log2,
            log3 = log3,
            log5 = log5,
            log10 = log10,
        )

        IntRange(0, 100).forEach {
            val x = Random.nextDouble(-10000.0, -0.000000001).toBigDecimal()
            assertAlmostEqual(
                actual = function.value(x),
                expected = fn.valueDecomposed(x),
            )
        }
    }

    @Test
    @Order(3)
    fun `mock cos test`() {
        val fn = Function(
            ln = ln,
            cosine = MockFunction(cosine),
            secant = secant,
            log2 = log2,
            log3 = log3,
            log5 = log5,
            log10 = log10,
        )

        IntRange(0, 100).forEach {
            val x = Random.nextDouble(-10000.0, -0.000000001).toBigDecimal()
            assertAlmostEqual(
                actual = function.value(x),
                expected = fn.valueDecomposed(x),
            )
        }
    }

    @Test
    @Order(4)
    fun `integration test`() {
        IntRange(0, 100).forEach {
            val x = Random.nextDouble(-10000.0, -0.000000001).toBigDecimal()
            assertAlmostEqual(
                actual = function.value(x),
                expected = function.valueDecomposed(x),
            )
        }
    }
}
