package util

import Constants
import functions.MathFunction
import functions.base.NaturalLogarithm
import functions.base.Sinus
import functions.nonbase.Cosine
import functions.nonbase.Logarithm
import functions.nonbase.Secant
import org.junit.jupiter.api.Test
import testing.TestValues
import java.io.FileOutputStream
import java.io.OutputStream
import java.math.BigDecimal
import java.math.RoundingMode

class CsvWriter {
    private val update = true
    private val baseDir = "src/test/resources"

    @Test
    fun writeAll() {
        if (!update) {
            return
        }

        // SIN
        val sin = Sinus()
        writeResults(sin, "sin")

        // COS
        val cos = Cosine(sin)
        writeResults(sin, "cos")

        // SECANT
        val secant = Secant(cos)
        writeResults(secant, "secant")

        // LN
        val ln = NaturalLogarithm()
        writeResults(ln, "ln")

        // Log2
        val log2 = Logarithm(ln, Constants.Numbers.TWO)
        writeResults(log2, "log2")

        // Log3
        val log3 = Logarithm(ln, Constants.Numbers.THREE)
        writeResults(log3, "log3")

        // Log5
        val log5 = Logarithm(ln, Constants.Numbers.FIVE)
        writeResults(log5, "log5")

        // Log10
        val log10 = Logarithm(ln, Constants.Numbers.TEN)
        writeResults(log10, "log10")
    }

    private fun writeResults(function: MathFunction, filename: String) {
        val results = mutableListOf<Pair<String, String>>()
        TestValues.X.forEach {
            try {
                val xBigDecimal = it.toBigDecimal().setScale(Constants.SCALE, RoundingMode.HALF_UP)
                results.add(
                    it.toString() to function.valueDecomposed(xBigDecimal)
                        .setScale(Constants.SCALE, RoundingMode.HALF_UP).toString()
                )
            } catch (e: Exception) {
                e.printStackTrace()
                results.add(it.toString() to "NaN")
            }
        }
        FileOutputStream("$baseDir/$filename.csv").writeCsv(results)
    }

    private fun OutputStream.writeCsv(xAndY: List<Pair<String, String>>) {
        val writer = bufferedWriter()
        xAndY.forEach {
            writer.write("${it.first}, ${it.second}")
            writer.newLine()
        }
        writer.flush()
    }

    companion object {
        val EPS = arrayListOf<Double>(
            0.000001,
            0.0000001,
            0.00000001
        )
    }

}
