package task1

import exceptions.NumberDoesNotCorrespondToTheDataAreaException
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class ArcSinFunctionTest {
    private lateinit var function: ArcSinFunction

    @BeforeAll
    fun tearUp() {
        function = ArcSinFunction()
    }

    // x = 1
    @ParameterizedTest
    @ValueSource(ints = [10, 15, 30, 45])
    fun `when x = 1, should return something close to PI div 2`(n: Int) {
        val x = 1.0

        val res = function.calculate(x, n)

        assertThat(res).isBetween(1.4, Math.PI / 2.0)
    }

    @Test
    fun `when x = 1, and N is great, should return almost PI div 2`() {
        val x = 1.0
        val n = 1_000
        val epsilon = 1e-1

        val result = function.calculate(x, n)

        assertThat(result).isBetween(Math.PI / 2.0 - epsilon, Math.PI / 2.0 + epsilon)
    }

    //  x = 0
    @ParameterizedTest
    @ValueSource(doubles = [-0.01, 0.0, 0.01])
    fun `when x is close to 0, should return something close to 0`(x: Double) {
        val n = 1000
        val epsilon = 1e-2

        val res = function.calculate(x, n)
        println(res)
        assertThat(res).isBetween(0.0 - epsilon, 0.0 + epsilon)
    }

    // x = -1
    @ParameterizedTest
    @ValueSource(ints = [10, 15, 30, 45])
    fun `when x = -1, should return something close to -(PI div 2)`(n: Int) {
        val x = -1.0

        val res = function.calculate(x, n)

        assertThat(res).isBetween(-Math.PI / 2.0, -1.4)
    }

    @Test
    fun `when x = -1, and N is great, should return almost -(PI div 2)`() {
        val x = -1.0
        val n = 1_000
        val epsilon = 1e-1

        val result = function.calculate(x, n)

        assertThat(result).isBetween(-Math.PI / 2.0 - epsilon, -Math.PI / 2.0 + epsilon)
    }

    // x > 0 && x < 1
    @Test
    fun `arcsin(x) should increase value from 0 to 1`() {
        val step = 0.01
        var cur = 0.1
        var prev = function.calculate(cur, 100)

        while (cur < 1.0) {
            val result = function.calculate(cur, 100)
            assertThat(result).isGreaterThanOrEqualTo(prev)
            assertThat(result).isBetween(0.0, Math.PI / 2.0)
            prev = result
            cur += step
        }
    }

    // x > -1 && x < 0
    @Test
    fun `arcsin(x) should increase value from -1 to 0`() {
        val step = 0.01
        var cur = -0.9
        var prev = function.calculate(cur, 100)

        while (cur < 0.0) {
            val result = function.calculate(cur, 100)
            assertThat(result).isGreaterThanOrEqualTo(prev)
            assertThat(result).isBetween(-Math.PI / 2.0, 0.0)
            prev = result
            cur += step
        }
    }

    // x > 1
    @DisplayName("Неккоретный ввод: x > 1 ")
    @Test
    fun testWithIncorrectInputOne() {
        val x = 1.001
        val n = 1

        assertThrows<NumberDoesNotCorrespondToTheDataAreaException> {
            function.calculate(x, n)
        }
    }

    // x < 1
    @DisplayName("Неккоретный ввод: x < -1 ")
    @Test
    fun testWithIncorrectInputTwo() {
        val x = -1.001
        val n = 1

        assertThrows<NumberDoesNotCorrespondToTheDataAreaException> {
            function.calculate(x, n)
        }
    }
}

