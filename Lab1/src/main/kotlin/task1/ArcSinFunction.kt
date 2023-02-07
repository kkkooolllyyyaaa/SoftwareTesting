package task1

import exceptions.NumberDoesNotCorrespondToTheDataAreaException
import kotlin.math.pow

class ArcSinFunction {
    companion object {
        private var cache = hashMapOf<Int, Int>()
        fun calculate(x: Double, n: Double): Double {
            if (x < -1 || x > 1) {
                throw NumberDoesNotCorrespondToTheDataAreaException();
            }
            if (n == 0.0)
                return x;
            var res = 0.0;
            for (i in 0..n.toInt()) {
                val numerator = factorial(2 * i)
                val denominator = 4.0.pow(i.toDouble()) * factorial(i).toDouble().pow(2.0) * (2 * i + 1)
                res += x.pow(2 * i + 1) * (numerator / denominator)
            }
            return res;
        }

        private fun factorial(number: Int): Int {
            if (!cache.containsKey(number)) {
                cache[number] = if (number <= 1) 1 else number * factorial(number - 1)
            }
            return cache[number]!!
        }
    }
}