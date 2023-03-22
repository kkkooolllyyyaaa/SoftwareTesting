package functions.nonbase

import Constants
import Constants.SCALE
import functions.base.Sinus
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource
import util.assertAlmostEqual
import java.math.BigDecimal
import java.math.RoundingMode
import kotlin.random.Random

class CosineTest {
    val cos = Cosine(Sinus())

    @Test
    fun `cos 0`() {
        assertAlmostEqual(
            actual = cos.valueDecomposed(BigDecimal.ZERO),
            expected = BigDecimal.ONE,
        )
    }

    @Test
    fun `cos pi div 6`() {
        val x = Constants.Numbers.PI.divide(6.0.toBigDecimal(), SCALE, RoundingMode.HALF_UP)
        assertAlmostEqual(
            actual = cos.valueDecomposed(x, eps = 0.000001.toBigDecimal()),
            expected = 0.866025,
        )
    }

    @Test
    fun `cos pi div 3`() {
        val x = Constants.Numbers.PI.divide(3.0.toBigDecimal(), SCALE, RoundingMode.HALF_UP)
        assertAlmostEqual(
            actual = cos.valueDecomposed(x, eps = 0.000001.toBigDecimal()),
            expected = 0.5,
        )
    }

    @Test
    fun `cos pi div 4`() {
        val x = Constants.Numbers.PI.divide(4.0.toBigDecimal(), SCALE, RoundingMode.HALF_UP)
        assertAlmostEqual(
            actual = cos.valueDecomposed(x, eps = 0.000001.toBigDecimal()),
            expected = 0.70710,
        )
    }

    @Test
    fun `cos pi div 2`() {
        val x = Constants.Numbers.PI.divide(2.0.toBigDecimal(), SCALE, RoundingMode.HALF_UP)
        assertAlmostEqual(
            actual = cos.valueDecomposed(x, eps = 0.000001.toBigDecimal()),
            expected = BigDecimal.ZERO,
        )
    }

    @Test
    fun `cos pi`() {
        assertAlmostEqual(
            actual = cos.valueDecomposed(Constants.Numbers.PI),
            expected = -BigDecimal.ONE,
        )
    }

    @ParameterizedTest
    @ValueSource(doubles = [0.0, 0.05, 0.1, 0.15, 0.20, 0.25, 0.30, 0.35, 0.40, 0.45, 0.50, 0.55, 0.60, 0.65, 0.70, 0.75, 0.80, 0.85, 0.90, 0.95, 1.0, 1.10, 1.20, 1.30, 1.40, 1.50, 1.60, 1.70, 1.80, 1.90, 2.0, 2.5, 3.0, 3.5, 4.0])
    fun `cos is even function test`(source: Double) {
        assertAlmostEqual(
            actual = cos.valueDecomposed(source.toBigDecimal()),
            expected = cos.valueDecomposed((-source).toBigDecimal()),
        )
    }

    @Test
    fun `random test`() {
        IntRange(0, 100).forEach {
            val x = Random.nextDouble(-1000.0, 1000.0).toBigDecimal()
            assertAlmostEqual(
                actual = cos.valueDecomposed(x),
                expected = cos.value(x),
            )
        }
    }
}
