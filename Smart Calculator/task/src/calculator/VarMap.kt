package calculator

import java.math.BigInteger

val varMap = mutableMapOf<String, String>()

fun saveVar(args: List<String>) {
    if (args.size>2) throw IllegalArgumentException("Invalid assignment")
    val (s,v) = args.map { it -> it.trim() }
    val regIdentifier = "[a-zA-Z]+".toRegex()
    val regAssignment = """^-?[0-9]+|[a-zA-Z]+""".toRegex()
    if (!s.matches(regIdentifier)) throw IllegalArgumentException("Invalid identifier")
    if (!v.matches(regAssignment)) throw IllegalArgumentException("Invalid assignment")
    if (v.matches(regIdentifier) && !varMap.containsKey(v)) throw IllegalArgumentException("Unknown variable")
    if (v.matches(regIdentifier)) {
        getVar(v)
    }
    varMap[s] = v
}

fun getVar(s: String): BigInteger {
    var temp = varMap.getOrDefault(s, "")
    if (temp.isEmpty()) throw IllegalArgumentException("Unknown variable")
    var rez = if (temp.matches("[a-zA-Z]+".toRegex())) {
        getVar(temp)
    } else {
        temp.toBigInteger()
    }
    return rez
}

