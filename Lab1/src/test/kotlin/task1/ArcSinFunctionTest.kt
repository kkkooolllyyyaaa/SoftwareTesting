package task1

import org.junit.jupiter.api.Test
import kotlin.math.asin

class ArcSinFunctionTest {
    @Test
    fun firstEdgeCase() {
        val x = 1.0
        val n = 20.0
        val res = ArcSinFunction.calculate(x,n)
        val trueRes = asin(x)
        println(res)
        println(trueRes)
    }

}