package functions.nonbase

import exception.InputValidationException
import functions.base.NaturalLogarithm
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvFileSource
import util.CsvWriter
import util.assertAlmostEqual
import util.isBigDecimal
import java.math.BigDecimal
import kotlin.test.Test
import kotlin.test.assertFailsWith

class LogarithmTest {
    private val naturalLogarithm = NaturalLogarithm()

    @ParameterizedTest
    @CsvFileSource(resources = ["/log2.csv"])
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

    @ParameterizedTest
    @CsvFileSource(resources = ["/log3.csv"])
    fun `logarithm_3_test`(value: BigDecimal, expected: String) {
        val logarithm = Logarithm(naturalLogarithm, BigDecimal(3))
        if (isBigDecimal(expected))
            assertAlmostEqual(BigDecimal(expected), logarithm.value(value))
        else
            assertFailsWith<InputValidationException>(
                message = "Логарифм от отрицательного числа",
                block = { logarithm.value(value) }
            )
    }

    @ParameterizedTest
    @CsvFileSource(resources = ["/log5.csv"])
    fun `logarithm_5_test`(value: BigDecimal, expected: String) {
        val logarithm = Logarithm(naturalLogarithm, BigDecimal(5))
        if (isBigDecimal(expected))
            assertAlmostEqual(BigDecimal(expected), logarithm.value(value))
        else
            assertFailsWith<InputValidationException>(
                message = "Логарифм от отрицательного числа",
                block = { logarithm.value(value) }
            )
    }

    @ParameterizedTest
    @CsvFileSource(resources = ["/log10.csv"])
    fun `logarithm_10_test`(value: BigDecimal, expected: String) {
        val logarithm = Logarithm(naturalLogarithm, BigDecimal(10))
        if (isBigDecimal(expected))
            assertAlmostEqual(BigDecimal(expected), logarithm.value(value))
        else
            assertFailsWith<InputValidationException>(
                message = "Логарифм от отрицательного числа",
                block = { logarithm.value(value) }
            )
    }

    @ParameterizedTest
    @CsvFileSource(resources = ["/log2.csv"])
    fun `logarithm_2_decomposed_eps_test`(value: BigDecimal, expected: String) {
        val logarithm = Logarithm(naturalLogarithm, BigDecimal(2))
        if (isBigDecimal(expected))
            assertAlmostEqual(BigDecimal(expected), logarithm.valueDecomposed(value))
        else
            assertFailsWith<InputValidationException>(
                message = "Логарифм от отрицательного числа",
                block = { logarithm.value(value) }
            )
    }

    @ParameterizedTest
    @CsvFileSource(resources = ["/log3.csv"])
    fun `logarithm_3_decomposed_eps_test`(value: BigDecimal, expected: String) {
        val logarithm = Logarithm(naturalLogarithm, BigDecimal(3))
        if (isBigDecimal(expected))
            assertAlmostEqual(BigDecimal(expected), logarithm.valueDecomposed(value))
        else
            assertFailsWith<InputValidationException>(
                message = "Логарифм от отрицательного числа",
                block = { logarithm.value(value) }
            )
    }

    @ParameterizedTest
    @CsvFileSource(resources = ["/log5.csv"])
    fun `logarithm5_decomposed_eps_test`(value: BigDecimal, expected: String) {
        val logarithm = Logarithm(naturalLogarithm, BigDecimal(5))
        if (isBigDecimal(expected))
            assertAlmostEqual(BigDecimal(expected), logarithm.valueDecomposed(value))
        else
            assertFailsWith<InputValidationException>(
                message = "Логарифм от отрицательного числа",
                block = { logarithm.value(value) }
            )
    }

    @ParameterizedTest
    @CsvFileSource(resources = ["/log10.csv"])
    fun `logarithm_10_decomposed_eps_test`(value: BigDecimal, expected: String) {
        val logarithm = Logarithm(naturalLogarithm, BigDecimal(10))
        if (isBigDecimal(expected))
            assertAlmostEqual(BigDecimal(expected), logarithm.valueDecomposed(value))
        else
            assertFailsWith<InputValidationException>(
                message = "Логарифм от отрицательного числа",
                block = { logarithm.value(value) }
            )
    }

    @ParameterizedTest
    @CsvFileSource(resources = ["/log2.csv"])
    fun `logarithm_2_decomposed_n_test`(value: BigDecimal, expected: String) {
        val logarithm = Logarithm(naturalLogarithm, BigDecimal(2))
        if (isBigDecimal(expected))
            assertAlmostEqual(BigDecimal(expected), logarithm.valueDecomposed(value, 200))
        else
            assertFailsWith<InputValidationException>(
                message = "Логарифм от отрицательного числа",
                block = { logarithm.value(value) }
            )
    }

    @ParameterizedTest
    @CsvFileSource(resources = ["/log3.csv"])
    fun `logarithm_3_decomposed_n_test`(value: BigDecimal, expected: String) {
        val logarithm = Logarithm(naturalLogarithm, BigDecimal(3))
        if (isBigDecimal(expected))
            assertAlmostEqual(BigDecimal(expected), logarithm.valueDecomposed(value, 200))
        else
            assertFailsWith<InputValidationException>(
                message = "Логарифм от отрицательного числа",
                block = { logarithm.value(value) }
            )
    }

    @ParameterizedTest
    @CsvFileSource(resources = ["/log5.csv"])
    fun `logarithm_5_decomposed_n_test`(value: BigDecimal, expected: String) {
        val logarithm = Logarithm(naturalLogarithm, BigDecimal(5))
        if (isBigDecimal(expected))
            assertAlmostEqual(BigDecimal(expected), logarithm.valueDecomposed(value, 200))
        else
            assertFailsWith<InputValidationException>(
                message = "Логарифм от отрицательного числа",
                block = { logarithm.value(value) }
            )
    }

    @ParameterizedTest
    @CsvFileSource(resources = ["/log10.csv"])
    fun `logarithm_10_decomposed_n_test`(value: BigDecimal, expected: String) {
        val logarithm = Logarithm(naturalLogarithm, BigDecimal(10))
        if (isBigDecimal(expected))
            assertAlmostEqual(BigDecimal(expected), logarithm.valueDecomposed(value, 200))
        else
            assertFailsWith<InputValidationException>(
                message = "Логарифм от отрицательного числа",
                block = { logarithm.value(value) }
            )
    }

}