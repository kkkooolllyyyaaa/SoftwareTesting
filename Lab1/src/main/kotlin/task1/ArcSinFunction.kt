package task1

import exceptions.NumberDoesNotCorrespondToTheDataAreaException
import kotlin.math.pow

class ArcSinFunction {
    companion object {
        private var cache = hashMapOf<Long, Long>()
        fun calculate(x: Double, n: Long): Double {
            if (x < -1 || x > 1) {
                throw NumberDoesNotCorrespondToTheDataAreaException()
            }
            if (n == 0L)
                return x

            var res = 0.0;
            for (i in 0..n) {
                val numerator = factorial(2 * i).toDouble()

                val denominator = 4.0.pow(i.toDouble()) * factorial(i).toDouble().pow(2.0) * (2 * i + 1)
                res += (x.pow((2 * i + 1).toDouble()) * numerator) / denominator
            }
            return res;
        }

        private fun factorial(number: Long): Long {
            if (!cache.containsKey(number)) {
                cache[number] = if (number <= 1) 1 else number * factorial(number - 1)
            }
            return cache[number]!!
        }
    }
}