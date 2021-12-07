package day6.fr

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


fun main() {

    //val vIn = readInput("input6")
    val vIn = readInput("test6")

    var lstFish = vIn[0].split(',').map { it.toInt() } as MutableList
    var lstWork = mutableListOf<Int>()

    var rep = 0L
    val nJours = 18

    for (i in 1 .. nJours) {
        var nbFish = lstFish.size

        for (j in 0 until nbFish) {
            var vCycle = lstFish[j]
            if (vCycle == 0) {
                lstWork.add(6)
                lstWork.add(8)
            }
            else {
                lstWork.add(vCycle - 1)
            }
        }

        lstFish = lstWork.toMutableList()
        lstWork.clear()
    }

    rep = lstFish.size.toLong()

    println(rep)
}


