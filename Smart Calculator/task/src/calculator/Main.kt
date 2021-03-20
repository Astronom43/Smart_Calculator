package calculator

import java.lang.IllegalArgumentException

fun main() {
    while (true) {
        val s = readLine()!!
        if (s.length == 0) {
            continue
        }
        if (s[0] == '/') {
            when {
                s == "/exit" -> break
                s == "/help" -> println(
                    "The program allows you to perform the following actions with numbers (+, - , * , / , % , ^ )"
                )
                else -> println("Unknown command")
            }

        } else {
            try {
                if (s.contains('=')) {
                    saveVar(s.split("="))
                } else {
                    val s1 = toDecode(s)
                    println(toExe(s1))
                }
            } catch (e: IllegalArgumentException) {
                println(e.message)
            }

        }

    }
    println("Bye!")
}








