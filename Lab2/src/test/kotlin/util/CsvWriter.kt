package util

import functions.base.Sinus
import org.junit.jupiter.api.Test
import java.io.FileOutputStream
import java.io.OutputStream

class CsvWriter {
    private val update = true
    private val baseDir = "src/test/resources"

    private val testingValues = listOf(
        -100.0,
        100.0,
        -50.0,
        50.0,
        -25.0,
        25.0,
        -10.0,
        10.0,
        -6.28318,
        6.28318,
        -5.0,
        5.0,
        -3.14159,
        3.14159,
        -1.57079,
        1.57079,
        -1.04719,
        1.04719,
        -0.78539,
        0.78539,
        -0.52359,
        0.52359,
        -0.25,
        0.25,
        -0.1,
        0.1,
    )

    @Test
    fun writeAll() {
        if (!update) {
            return
        }

        val sin = Sinus()
        var results = mutableListOf<Pair<Double, Double>>()
        testingValues.forEach {
            results.add(it to sin.valueDecomposed(it.toBigDecimal()).toDouble())
        }
        FileOutputStream("$baseDir/sin.csv").writeCsv(results)

        results = mutableListOf()
    }

    fun OutputStream.writeCsv(xAndY: List<Pair<Double, Double>>) {
        val writer = bufferedWriter()
        xAndY.forEach {
            writer.write("${it.first}, ${it.second}")
            writer.newLine()
        }
        writer.flush()
    }
}