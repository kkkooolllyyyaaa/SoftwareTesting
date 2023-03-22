package functions.nonbase

import exception.InputValidationException
import functions.base.NaturalLogarithm
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvFileSource
import util.assertAlmostEqual
import util.isBigDecimal
import java.math.BigDecimal
import kotlin.test.assertFailsWith

class LogarithmTest {
    private val naturalLogarithm = NaturalLogarithm()

    @ParameterizedTest
    @CsvFileSource(resources = ["/Logarithm.csv"])
    fun `logarithm_2_test`(value: BigDecimal, expected: String) {
        val logarithm = Logarithm(naturalLogarithm, BigDecimal(2))
        if (isBigDecimal(expected))
            assertAlmostEqual(BigDecimal(expected), logarithm.value(value))
        else
            assertFailsWith<InputValidationException>(
                message = "Логарифм от отрицательного числа",
                block = { logarithm.value(value) }
            )
    }
}