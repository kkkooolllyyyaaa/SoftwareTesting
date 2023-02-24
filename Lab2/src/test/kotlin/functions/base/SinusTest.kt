package functions.base

import org.junit.jupiter.api.Test
import solution.tables.Factorial
import java.math.RoundingMode
import kotlin.random.Random

class SinusTest {

    private val sinus: Sinus = Sinus(Factorial())

    @Test
    fun `sin(x) eps test`() {
        var cur = -2 * Math.PI
        IntRange(0, 5).forEach {
            val delta = Random.nextDouble(0.1, Math.PI / 4.0)

            val result = sinus.valueDecomposed(cur.toBigDecimal(), 0.01.toBigDecimal())
            val expected = sinus.value(cur.toBigDecimal())
            println(
                """
                       x = $cur
                       y = ${result.setScale(5, RoundingMode.UP)}
                expected = ${expected.setScale(5, RoundingMode.UP)}
                
                """.trimIndent()
            )

            cur += delta
        }
    }

    @Test
    fun `sin(x) N test`() {
        var cur = -2 * Math.PI
        IntRange(0, 5).forEach {
            val delta = Random.nextDouble(0.0, Math.PI / 4.0)
            val n = 100
            val result = sinus.valueDecomposed(cur.toBigDecimal(), n)
            val expected = sinus.value(cur.toBigDecimal())
            println(
                """
                       x = $cur
                       y = ${result.setScale(5, RoundingMode.UP)}
                expected = ${expected.setScale(5, RoundingMode.UP)}
                
                """.trimIndent()
            )

            cur += delta
        }
    }
}