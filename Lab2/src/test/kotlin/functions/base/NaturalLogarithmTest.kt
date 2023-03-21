package functions.base

import org.junit.jupiter.api.Test
import java.math.RoundingMode
import kotlin.random.Random

class NaturalLogarithmTest {

    private val naturalLogarithm = NaturalLogarithm()

    @Test
    fun `ln(x) eps test`() {
        var cur = 0.1
        IntRange(0, 10).forEach {
            val delta = Random.nextDouble(0.1, 1.0)

            val result = naturalLogarithm.valueDecomposed(cur.toBigDecimal(), 0.01.toBigDecimal())
            val expected = naturalLogarithm.value(cur.toBigDecimal())
            println(
                """
                       x = $cur
                       y = ${result.setScale(5, RoundingMode.HALF_UP)}
                expected = ${expected.setScale(5, RoundingMode.HALF_UP)}
                
                """.trimIndent()
            )

            cur += delta
        }
    }

    @Test
    fun `ln(x) N test`() {
        var cur = 0.1
        IntRange(0, 10).forEach {
            val delta = Random.nextDouble(0.1, 1.0)
            val n = 100
            val result = naturalLogarithm.valueDecomposed(cur.toBigDecimal(), n)
            val expected = naturalLogarithm.value(cur.toBigDecimal())
            println(
                """
                       x = $cur
                       y = ${result.setScale(5, RoundingMode.HALF_UP)}
                expected = ${expected.setScale(5, RoundingMode.HALF_UP)}
                
                """.trimIndent()
            )

            cur += delta
        }
    }
}
