package task1

import exceptions.NumberDoesNotCorrespondToTheDataAreaException
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

class ArcSinFunctionTest {
    @ParameterizedTest
    @ValueSource(longs = [10, 15, 30])
    fun firstEdgeCase(n: Long) {
        val x = 1.0
        val res = ArcSinFunction.calculate(x, n)

        assertThat(res).isBetween(1.39, 1.41)
    }

    @DisplayName("Неккоретный ввод: x >1 ")
    @Test
    fun testWithIncorrectInputOne() {
        val x = 1.1
        val n = 1L
        assertThrows<NumberDoesNotCorrespondToTheDataAreaException> {
            ArcSinFunction.calculate(x, n)
        }
    }

    @DisplayName("Неккоретный ввод: x <-1 ")
    @Test
    fun testWithIncorrectInputTwo() {
        val x = -1.1
        val n = 1L
        assertThrows<NumberDoesNotCorrespondToTheDataAreaException> {
            ArcSinFunction.calculate(x, n)
        }
    }

}