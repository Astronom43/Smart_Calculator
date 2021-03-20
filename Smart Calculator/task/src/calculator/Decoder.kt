package calculator

import java.math.BigInteger
import java.util.*

fun toDecode(s: String): String {
    val stack = Stack<String>()
    val list = parseStr(s)
    var rez = ""
    for (str in list) {
        try {
            var i = "0".toBigInteger()
            if (str.matches("[a-zA-Z]+".toRegex())) {
                i = getVar(str)
            } else {
                i = str.toBigInteger()
            }
            rez += "$i "
        } catch (e: Exception) {
            if (e.message == "Unknown variable") throw IllegalArgumentException(e.message)

            if (str == "(") {
                stack.push(str)
                continue
            }
            if (str == ")") {
                try {
                    while (true) {
                        val a = stack.pop()
                        if (a == "(") {
                            break
                        }
                        rez += "$a "
                    }
                } catch (e: EmptyStackException){
                    throw IllegalArgumentException("Invalid expression")
                }
                continue
            }
            try {
                while (opStatus(stack.peek()) >= opStatus(str)) {
                    rez += "${stack.pop()} "
                }
            } catch (e: EmptyStackException) {
            } finally {
                stack.myPush(str)
            }
        }
    }
    while (!stack.empty()) {
        rez += "${stack.pop()} "
    }
    if (rez.length == 0) throw IllegalArgumentException("Invalid expression")
    return rez.trim()
}

fun parseStr(s: String): List<String> {
    val item = """-+*/()^"""
    val bracket = """()"""
    val rez = mutableListOf<String>()
    val s1 = StringBuilder()
    s.forEach { c -> if (c != ' ') s1.append(c) }
    if (s1[0] == '-') {
        s1.insert(0, 0)
    }
    val digQueue: Queue<String> = LinkedList<String>()
    val itemQueue: Queue<String> = LinkedList<String>()

    for (c in s1) {
        if (item.contains(c)) {
            rez.add(clearQueue(digQueue))
            if (bracket.contains(c)) {
                rez.add(clearQueue(itemQueue))
                rez.add("" + c)
            } else {
                itemQueue.offer("" + c)
            }
        } else {
            rez.add(clearQueue(itemQueue))
            digQueue.offer("" + c)
        }
    }

    rez.add(clearQueue(itemQueue))
    rez.add(clearQueue(digQueue))

    return rez.filter { it -> it != "" }
}

private fun clearQueue(queue: Queue<String>): String {
    var tmpDig = ""
    if (!queue.isEmpty()) {
        while (!queue.isEmpty()) {
            tmpDig += queue.poll()
        }
    }
    return tmpDig
}

fun opStatus(c: String): Int {
    when (c) {
        in "^" -> return 4
        in "*/%" -> return 3
        in "-+" -> return 2
        in "()" -> return 1
        else -> return 0
    }
}



