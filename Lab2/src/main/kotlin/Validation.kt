import java.math.BigDecimal

fun assertXNotZero(x: BigDecimal) {
    if (x.abs() >= BigDecimal.ZERO && x.abs() <= 0.00000000001.toBigDecimal()) {
        throw ArithmeticException("/ by zero")
    }
}
