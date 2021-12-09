package day9.fr

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

data class Bool4(var vrai: Boolean) {

    var p1 = true
    var p2 = true
    var p3 = true
    var p4 = true

    fun toutVrai() : Boolean {
        vrai = (p1 && p2 && p3 && p4)
        return vrai
    }
}


fun main() {

    var rep = 0L

    val vIn = readInput("input9")
    //val vIn = readInput("test9")

    val nL = vIn[0].length
    val nH = vIn.size

    var tabPoints: Array<Array<Int>> = Array (nH + 1) { Array(nL + 1) { -1 } }

    vIn.forEachIndexed { idx, it ->
        it.forEachIndexed { index, ch ->
            tabPoints[idx + 1][index + 1] = ch.code - 48
        }
    }

    for (i in 1..nH) {
        for (j in 1 .. nL) {

            var v = tabPoints[i][j]
            var isVrai = false
            var verite = Bool4(isVrai)

            if ((i - 1) > 0) {
                if (tabPoints[i - 1][j] <= v) {
                    verite.p1 = false
                }
            }

            if ((j - 1) > 0) {
                if (tabPoints[i][j - 1] <= v) {
                        verite.p2 = false
                    }
            }

            if ((j + 1) <= nL) {
                if (tabPoints[i][j + 1] <= v) {
                        verite.p3 = false
                    }
            }

            if ((i + 1) <= nH) {
                if (tabPoints[i + 1][j] <= v) {
                        verite.p2 = false
                    }
            }

            if (verite.toutVrai()) {
                rep += (1 + v)
            }
        }
    }

    println(rep)
}

