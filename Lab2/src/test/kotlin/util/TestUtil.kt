package util

import org.assertj.core.api.Assertions.assertThat
import java.math.BigDecimal

fun assertAlmostEqual(actual: BigDecimal, expected: Double, eps: Double = 0.0001) {
    assertAlmostEqual(actual, expected.toBigDecimal(), eps)
}

fun assertAlmostEqual(actual: BigDecimal, expected: BigDecimal, eps: Double = 0.0001) {
    val left = expected - eps.toBigDecimal()
    val right = expected + eps.toBigDecimal()
    assertThat(
        actual
    ).isBetween(
        left.min(right),
        left.max(right),
    )
}

fun isBigDecimal(value: String?): Boolean {
    return formatBigDecimal(value) != null
}

fun formatBigDecimal(value: String?): BigDecimal? {
    return if (value == null) {
        null
    } else try {
        BigDecimal(value)
    } catch (e: NumberFormatException) {
        null
    }
}