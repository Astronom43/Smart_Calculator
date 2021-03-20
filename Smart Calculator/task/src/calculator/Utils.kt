package calculator

import java.util.*

fun Stack<String>.myPush(s: String): Unit {
    when {
        s.all { c -> c == '-' } -> {
            if (s.length % 2 == 0) {
                this.push("+")
            } else {
                this.push("-")
            }
        }
        s.all { c -> c == '+' } -> this.push("+")
        s.all { c -> c == '*' } -> {
            s.forEach { c -> this.push(""+c) }     }
        s.all { c -> c == '/' } -> {
            s.forEach { c -> this.push(""+c)}}

        s.all { c -> c == '(' } -> {
               s.forEach {  this.push("(")}
            }
        s.all { c -> c == '^' } -> {
            s.forEach {  this.push("^")}
        }
    }
}