package functions.nonbase

import functions.base.NaturalLogarithm
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvFileSource
import util.assertAlmostEqual
import java.math.BigDecimal

class LogarithmTest {
    val naturalLogarithm = NaturalLogarithm()

    @ParameterizedTest
    @CsvFileSource(resources = ["/Logarithm.csv"])
    fun `logarithm_2_test`(value: BigDecimal, expected: BigDecimal) {
        val logarithm = Logarithm(naturalLogarithm, BigDecimal(2))
        assertAlmostEqual(expected, logarithm.value(value).toDouble())
    }
}