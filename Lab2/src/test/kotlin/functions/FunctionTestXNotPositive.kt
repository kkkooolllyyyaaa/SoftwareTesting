package functions

import functions.base.NaturalLogarithm
import functions.base.Sinus
import functions.nonbase.Cosine
import functions.nonbase.Secant

class FunctionTestXNotPositive {
    private val ln = NaturalLogarithm()
    private val sinus = Sinus()
    private val cosine = Cosine(sinus)
    private val secant = Secant(cosine)

    private val function = Function(ln, cosine, secant)
}