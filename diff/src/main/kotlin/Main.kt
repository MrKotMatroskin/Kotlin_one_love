import kotlin.math.abs
import kotlin.math.cos
import kotlin.math.exp
import kotlin.math.sin

typealias Function = (DoubleArray) -> Double

class Differentiator(private val h: Double) {
    fun differentiate(f: Function, x: DoubleArray): Double {
        val xPlusH = x.map { it + h }.toDoubleArray()
        val xMinusH = x.map { it - h }.toDoubleArray()
        return (f(xPlusH) - f(xMinusH)) / (2 * h)
    }

    fun differentiateSecond(f: Function, x: DoubleArray): Double {
        val xPlusH = x.map { it + h }.toDoubleArray()
        val xMinusH = x.map { it - h }.toDoubleArray()
        return ((f(xPlusH) - 2 * f(x) + f(xMinusH) ) / (h * h))
    }
}

fun main() {
    val f1: Function = { x -> x[0] * x[0] }  // Пример функции f(x) = x^2
    val f2: Function = { x -> x[0] * x[0] * x[0] }  // Пример функции f(x) = x^3
    val f3: Function = { x -> sin(x[0]) }  // Пример функции f(x) = sin(x)
    val f4: Function = { x -> exp(x[0] * x[0]) }  // Пример функции f(x) = e^(x^2)
    val x: DoubleArray = doubleArrayOf(3.0)  // Точка, в которой вычисляем производные
    val h = 0.001  // Шаг для конечных разностей

    val differentiator = Differentiator(h)
    val firstDerivative1 = differentiator.differentiate(f1, x)
    val secondDerivative1 = differentiator.differentiateSecond(f1, x)
    val firstDerivative2 = differentiator.differentiate(f2, x)
    val secondDerivative2 = differentiator.differentiateSecond(f2, x)
    val firstDerivative3 = differentiator.differentiate(f3, x)
    val secondDerivative3 = differentiator.differentiateSecond(f3, x)
    val firstDerivative4 = differentiator.differentiate(f4, x)
    val secondDerivative4 = differentiator.differentiateSecond(f4, x)

    println("First derivative f1: $firstDerivative1")
    println("Second derivative f1: $secondDerivative1")
    println("First derivative f2: $firstDerivative2")
    println("Second derivative f2: $secondDerivative2")
    println("First derivative f3: $firstDerivative3")
    println("Second derivative f3: $secondDerivative3")
    println("First derivative f4: $firstDerivative4")
    println("Second derivative f4: $secondDerivative4")
}
