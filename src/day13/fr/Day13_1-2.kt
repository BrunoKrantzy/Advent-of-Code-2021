package day13.fr

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

    var rep = 0L

    //val vInGrille = day11.fr.readInput("test13a")
    //val vInFold = day11.fr.readInput("test13b")
    val vInGrille = day11.fr.readInput("input13a")
    val vInFold = day11.fr.readInput("input13b")

    var maxX = 0
    var maxY = 0

    var regex = "^(\\d+),(\\d+)".toRegex()

    vInGrille.forEach {
        val match = regex.find(it)
        val x = match!!.groups[1]!!.value.toInt()
        val y = match.groups[2]!!.value.toInt()

        maxX = maxOf(maxX, x)
        maxY = maxOf(maxY, y)
    }

    var tabTrans = Array (maxY + 1) { Array(maxX + 1) { '.' } }

    vInGrille.forEach {
        val match = regex.find(it)
        val x = match!!.groups[1]!!.value.toInt()
        val y = match.groups[2]!!.value.toInt()

        tabTrans[y][x] = '#'
    }

    var nLarg = maxX
    var nHaut = maxY

    for (f in 0 until vInFold.size) {
        var sFold = vInFold[f]
        regex = "^[a-z]+\\s[a-z]+\\s([xy])=(\\d+)".toRegex()
        val match = regex.find(sFold)
        val xy = match!!.groups[1]!!.value
        val vXY = match.groups[2]!!.value.toInt()

        if (xy == "y") { // pli hauteur
            var dif = (nHaut - vXY) * 2
            for (i in nHaut downTo vXY) {
                for (j in 0 .. nLarg) {
                    if (tabTrans[i][j] != '.') {
                        tabTrans[i-dif][j] = tabTrans[i][j]
                    }
                }
                dif -= 2
            }
            //nHaut = (nHaut - 1) / 2
            nHaut = (nHaut - vXY) - 1
        }
        else { // pli largeur
            for (i in 0 .. nHaut) {
                var dif = ((nLarg - vXY) * 2)
                for (j in nLarg downTo vXY) {
                    if (tabTrans[i][j-dif] != '#') {
                        tabTrans[i][j-dif] = tabTrans[i][j]
                    }
                    dif -= 2
                }
            }
            nLarg = (nLarg - vXY) - 1
        }
    }


    for (i in 0 .. nHaut) {
        for (j in 0 .. nLarg) {
            if (tabTrans[i][j] == '#') {
                rep++
            }
            print(tabTrans[i][j])
        }
        print("\n")
    }


    println(rep)
}


