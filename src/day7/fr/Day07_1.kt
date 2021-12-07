package day7.fr

import java.io.File
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


fun readInput(name: String) = File("src", "$name.txt").readLines()


fun main() {

    var rep = 0

    //val vIn = readInput("test7")
    val vIn = readInput("input7")

    var lstIn = vIn[0].split(',').map { it.toInt() } as MutableList
    lstIn.sort()

    var min = Int.MAX_VALUE
    var deb = lstIn.first()
    var fin = lstIn.last()

    for (i in deb..fin) {
        for (it in lstIn) {
            val dif = abs(i - it)
            rep += dif
        }
        min = minOf(min, rep)
        rep = 0
    }

    println(min)
}




