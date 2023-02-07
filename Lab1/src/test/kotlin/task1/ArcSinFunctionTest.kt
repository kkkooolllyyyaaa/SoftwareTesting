package task1

import exceptions.NumberDoesNotCorrespondToTheDataAreaException
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import kotlin.math.asin
import kotlin.test.assertEquals

class ArcSinFunctionTest {
    @Test
    fun firstEdgeCase() {
        val x = 1.0
        val n = 10.0
        val res = ArcSinFunction.calculate(x, n)
        val trueRes = asin(x)
        println(res)
        println(trueRes)
//        assertEquals()
    }

    @DisplayName("Неккоретный ввод: x >1 ")
    @Test
    fun testWithIncorrectInputOne() {
        val x = 1.1
        val n = 1.0
        assertThrows<NumberDoesNotCorrespondToTheDataAreaException> {
            ArcSinFunction.calculate(x, n)
        }
    }

    @DisplayName("Неккоретный ввод: x <-1 ")
    @Test
    fun testWithIncorrectInputTwo() {
        val x = -1.1
        val n = 1.0
        assertThrows<NumberDoesNotCorrespondToTheDataAreaException> {
            ArcSinFunction.calculate(x, n)
        }
    }

}