package calculator

import java.lang.Exception
import java.lang.IllegalArgumentException
import java.math.BigInteger
import java.util.*
import kotlin.math.pow


fun toExe(s: String): String {
    val arr = s.trim().split(" ").toTypedArray()
    var rez = Stack<BigInteger>()
    for (s in arr) {
        try {
            rez.push(s.toBigInteger())
        } catch (e: Exception) {
            try {
                val b = rez.pop()
                val a = rez.pop()
                val act = action(a, b, s)
                rez.push(act)
            } catch (e: EmptyStackException) {
                throw IllegalArgumentException("Invalid expression")
            }
        }

    }
    val r = rez.pop()
    if (!rez.empty()) throw IllegalArgumentException("Invalid expression")
    return "" + r
}

fun action(a: BigInteger, b: BigInteger, s: String): BigInteger {
    return when (s) {
        "+" -> return a + b
        "-" -> return a - b
        "*" -> return a * b
        "/" -> return a/b
        "^" -> return a.pow(b.toInt())
        else -> "0".toBigInteger()
    }


}
