package day10.fr

import java.io.File

private fun readChars(): CharArray = readLn().toCharArray()
private fun readLn() = readLine()!! // string line
private fun readSb() = StringBuilder(readLn())
private fun readInt() = readLn().toInt() // single int
private fun readLong() = readLn().toLong() // single long
private fun readDouble() = readLn().toDouble() // single double
private fun readStrings() = readLn().split(" ") // list of strings
private fun readInts() = readStrings().map { it.toInt() } // list of ints
private fun readLongs() = readStrings().map { it.toLong() } // list of longs
private fun readDoubles() = readStrings().map { it.toDouble() } // list of doubles
private fun readIntArray() = readStrings().map { it.toInt() }.toIntArray() // Array of ints
private fun readLongArray() = readStrings().map { it.toLong() }.toLongArray() // Array of longs


fun readInput(name: String) = File("src", "$name.txt").readLines()

class MutableStack<E>(vararg items: E) {
    private val elements = items.toMutableList()
    fun push(element: E) = elements.add(element)
    fun peek(): E = elements.last()
    fun pop(): E = elements.removeAt(elements.size - 1)
    fun isEmpty() = elements.isEmpty()
    fun size() = elements.size
    override fun toString() = "MutableStack(${elements.joinToString()})"
}



fun main() {

    var rep = 0L

    var pileCh = MutableStack<Char>()

    val vIn = readInput("input10")
    //val vIn = readInput("test10")

    vIn.forEach {
        for (element in it) {
            when (element) {
                '(' -> pileCh.push(element)
                ')' -> {
                    val v = pileCh.peek()
                    if (v == '(')
                        pileCh.pop()
                    else {
                        rep += 3
                        break
                    }
                }
                '[' -> pileCh.push(element)
                ']' -> {
                    val v = pileCh.peek()
                    if (v == '[')
                        pileCh.pop()
                    else {
                        rep += 57
                        break
                    }
                }
                '{' -> pileCh.push(element)
                '}' -> {
                    val v = pileCh.peek()
                    if (v == '{')
                        pileCh.pop()
                    else {
                        rep += 1197
                        break
                    }
                }
                '<' -> pileCh.push(element)
                '>' -> {
                    val v = pileCh.peek()
                    if (v == '<')
                        pileCh.pop()
                    else {
                        rep += 25137
                        break
                    }
                }
            }
        }
    }

    println(rep)
}


