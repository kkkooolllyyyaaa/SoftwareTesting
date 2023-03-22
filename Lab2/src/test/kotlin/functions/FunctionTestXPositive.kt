package functions

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
import java.math.RoundingMode
import kotlin.random.Random

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
    @Order(1)
    fun `ODZ x = 1`() {
        assertThrows<ArithmeticException> {
            function.value(BigDecimal.ONE)
        }
    }

    @Test
    @Order(2)
    fun `mock log 2 test`() {
        val fn = Function(
            ln = ln,
            cosine = cosine,
            secant = secant,
            log2 = MockFunction(log2),
            log3 = log3,
            log5 = log5,
            log10 = log10,
        )

        IntRange(2, 20).forEach {
            val x = Random.nextDouble(0.5, 100.0).toBigDecimal()
            assertAlmostEqual(
                actual = function.value(x),
                expected = fn.valueDecomposed(x)
            )
        }
    }

    @Test
    @Order(3)
    fun `mock log 3 test`() {
        val fn = Function(
            ln = ln,
            cosine = cosine,
            secant = secant,
            log2 = log2,
            log3 = MockFunction(log3),
            log5 = log5,
            log10 = log10,
        )

        IntRange(2, 20).forEach {
            val x = Random.nextDouble(0.5, 100.0).toBigDecimal()
            assertAlmostEqual(
                actual = function.value(x),
                expected = fn.valueDecomposed(x)
            )
        }
    }

    @Test
    @Order(4)
    fun `mock log 5 test`() {
        val fn = Function(
            ln = ln,
            cosine = cosine,
            secant = secant,
            log2 = log2,
            log3 = log3,
            log5 = MockFunction(log5),
            log10 = log10,
        )

        IntRange(2, 20).forEach {
            val x = Random.nextDouble(0.5, 100.0).toBigDecimal()
            assertAlmostEqual(
                actual = function.value(x),
                expected = fn.valueDecomposed(x)
            )
        }
    }

    @Test
    @Order(5)
    fun `mock log 10 test`() {
        val fn = Function(
            ln = ln,
            cosine = cosine,
            secant = secant,
            log2 = log2,
            log3 = log3,
            log5 = log5,
            log10 = MockFunction(log10),
        )

        IntRange(2, 20).forEach {
            val x = Random.nextDouble(0.5, 100.0).toBigDecimal()
            assertAlmostEqual(
                actual = function.value(x),
                expected = fn.valueDecomposed(x)
            )
        }
    }

    @Test
    @Order(6)
    fun `mock ln test`() {
        val fn = Function(
            ln = MockFunction(ln),
            cosine = cosine,
            secant = secant,
            log2 = log2,
            log3 = log3,
            log5 = log5,
            log10 = log10,
        )

        IntRange(2, 20).forEach {
            val x = Random.nextDouble(0.5, 100.0).toBigDecimal()
            assertAlmostEqual(
                actual = function.value(x),
                expected = fn.valueDecomposed(x)
            )
        }
    }

    @Test
    @Order(7)
    fun `mock all except ln test`() {
        val fn = Function(
            ln = ln,
            cosine = cosine,
            secant = secant,
            log2 = MockFunction(log2),
            log3 = MockFunction(log3),
            log5 = MockFunction(log5),
            log10 = MockFunction(log10),
        )

        IntRange(2, 20).forEach {
            val x = Random.nextDouble(0.5, 100.0).toBigDecimal()
            assertAlmostEqual(
                actual = function.value(x),
                expected = fn.valueDecomposed(x)
            )
        }
    }

    @Test
    @Order(8)
    fun `mock all except lg2 test`() {
        val fn = Function(
            ln = MockFunction(ln),
            cosine = cosine,
            secant = secant,
            log2 = log2,
            log3 = MockFunction(log3),
            log5 = MockFunction(log5),
            log10 = MockFunction(log10),
        )

        IntRange(2, 20).forEach {
            val x = Random.nextDouble(0.5, 100.0).toBigDecimal()
            println("x=$x y=${function.value(x).setScale(3, RoundingMode.HALF_UP)}")
            assertAlmostEqual(
                actual = function.value(x),
                expected = fn.valueDecomposed(x)
            )
        }
    }

    @Test
    @Order(9)
    fun `mock all except lg3 test`() {
        val fn = Function(
            ln = MockFunction(ln),
            cosine = cosine,
            secant = secant,
            log2 = MockFunction(log2),
            log3 = log3,
            log5 = MockFunction(log5),
            log10 = MockFunction(log10),
        )

        IntRange(2, 20).forEach {
            val x = Random.nextDouble(0.5, 100.0).toBigDecimal()
            assertAlmostEqual(
                actual = function.value(x),
                expected = fn.valueDecomposed(x)
            )
        }
    }

    @Test
    @Order(10)
    fun `mock all except lg5 test`() {
        val fn = Function(
            ln = MockFunction(ln),
            cosine = cosine,
            secant = secant,
            log2 = MockFunction(log2),
            log3 = MockFunction(log3),
            log5 = log5,
            log10 = MockFunction(log10),
        )

        IntRange(2, 20).forEach {
            val x = Random.nextDouble(0.5, 100.0).toBigDecimal()
            assertAlmostEqual(
                actual = function.value(x),
                expected = fn.valueDecomposed(x)
            )
        }
    }

    @Test
    @Order(11)
    fun `mock all except lg10 test`() {
        val fn = Function(
            ln = MockFunction(ln),
            cosine = cosine,
            secant = secant,
            log2 = MockFunction(log2),
            log3 = MockFunction(log3),
            log5 = MockFunction(log5),
            log10 = log10,
        )

        IntRange(2, 20).forEach {
            val x = Random.nextDouble(0.5, 100.0).toBigDecimal()
            assertAlmostEqual(
                actual = function.value(x),
                expected = fn.valueDecomposed(x)
            )
        }
    }

    @Test
    @Order(12)
    fun `mock all except lg2, lg3 test`() {
        val fn = Function(
            ln = MockFunction(ln),
            cosine = cosine,
            secant = secant,
            log2 = log2,
            log3 = log3,
            log5 = MockFunction(log5),
            log10 = MockFunction(log10),
        )

        IntRange(2, 20).forEach {
            val x = Random.nextDouble(0.5, 100.0).toBigDecimal()
            assertAlmostEqual(
                actual = function.value(x),
                expected = fn.valueDecomposed(x)
            )
        }
    }

    @Test
    @Order(13)
    fun `mock all except lg10, lg5 test`() {
        val fn = Function(
            ln = MockFunction(ln),
            cosine = cosine,
            secant = secant,
            log2 = MockFunction(log2),
            log3 = MockFunction(log3),
            log5 = log5,
            log10 = log10,
        )

        IntRange(2, 20).forEach {
            val x = Random.nextDouble(0.5, 100.0).toBigDecimal()
            assertAlmostEqual(
                actual = function.value(x),
                expected = fn.valueDecomposed(x)
            )
        }
    }

    @Test
    @Order(14)
    fun `test without mocks`() {
        val fn = Function(
            ln = ln,
            cosine = cosine,
            secant = secant,
            log2 = log2,
            log3 = log3,
            log5 = log5,
            log10 = log10,
        )

        IntRange(2, 20).forEach {
            val x = Random.nextDouble(0.5, 100.0).toBigDecimal()
            assertAlmostEqual(
                actual = function.value(x),
                expected = fn.valueDecomposed(x)
            )
        }
    }
}
