package util

import functions.base.Sinus
import org.junit.jupiter.api.Test
import testing.TestValues
import java.io.FileOutputStream
import java.io.OutputStream

class CsvWriter {
    private val update = true
    private val baseDir = "src/test/resources"

    @Test
    fun writeAll() {
        if (!update) {
            return
        }

        val sin = Sinus()
        var results = mutableListOf<Pair<Double, Double>>()
        TestValues.X.forEach {
            results.add(it to sin.valueDecomposed(it.toBigDecimal()).toDouble())
        }
        FileOutputStream("$baseDir/sin.csv").writeCsv(results)

        results = mutableListOf()
    }

    private fun OutputStream.writeCsv(xAndY: List<Pair<Double, Double>>) {
        val writer = bufferedWriter()
        xAndY.forEach {
            writer.write("${it.first}, ${it.second}")
            writer.newLine()
        }
        writer.flush()
    }
}