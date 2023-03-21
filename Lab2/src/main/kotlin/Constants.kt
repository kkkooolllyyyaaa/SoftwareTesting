import java.math.BigDecimal
import java.math.RoundingMode

object Constants {
    object Numbers {
        val TWO = BigDecimal.ONE + BigDecimal.ONE
        val THREE = TWO + BigDecimal.ONE
        val PI = Math.PI.toBigDecimal()
        val PI_DIV_2 = PI.divide(TWO, 20, RoundingMode.HALF_UP)
    }
}