package day7.fr

import kotlin.math.abs

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


fun main() {

    var rep = 0L

    //val vIn = readInput("test7")
    val vIn = readInput("input7")

    var lstIn = vIn[0].split(',').map { it.toInt() } as MutableList
    lstIn.sort()

    var bestPos = 0

    var tabV: Array<Array<Long>> = Array(2000) {Array(1) { 0 } }

    for (i in 1 until 2000) {
        var v = 0L
        var c = 1
        for (j in 1..i) {
            v += c++
        }
        tabV[i][0] = v
    }

    var min = Long.MAX_VALUE
    var deb = lstIn.first()
    var fin = lstIn.last()

    for (i in deb..fin) {
            for (it in lstIn) {
                val dif = abs(i - it)
                val sup = tabV[dif][0]
                rep += sup
            }

            val oldMin = min
            min = minOf(min, rep)
         rep = 0
    }

    println(min)
}

