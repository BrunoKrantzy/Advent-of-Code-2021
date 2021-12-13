package day11.fr

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

fun getAdjacents(ligne:Int, col:Int) : MutableList<Int> {
    var lst = mutableListOf<Int>()
    if (ligne == 1) {
        if (col == 1) {
            lst.add(2)
            lst.add(11)
            lst.add(12)
        }
        else if (col == 10) {
            lst.add(9)
            lst.add(19)
            lst.add(20)
        }
        else {
            lst.add(col - 1)
            lst.add(col + 1)
            lst.add((ligne*10) + (col-1))
            lst.add((ligne*10) + col)
            lst.add((ligne*10) + (col+1))
        }
    }
    else if (ligne == 10) {
        if (col == 1) {
            lst.add(81)
            lst.add(82)
            lst.add(92)
        }
        else if (col == 10) {
            lst.add(89)
            lst.add(90)
            lst.add(99)
        }
        else {
            lst.add(((ligne-1)*10) + (col+1))
            lst.add(((ligne-1)*10) + (col-1))
            lst.add(((ligne-2)*10) + (col+1))
            lst.add(((ligne-2)*10) + col)
            lst.add(((ligne-2)*10) + (col-1))
        }
    }
    else {
        if (col == 1) {
            lst.add(((ligne-2)*10) + col)
            lst.add(((ligne-2)*10) + (col+1))
            lst.add(((ligne-1)*10) + (col+1))
            lst.add((ligne*10) + col)
            lst.add((ligne*10) + (col+1))
        }
        else if (col == 10) {
            lst.add(((ligne-1)*10) - 1)
            lst.add((ligne-1)*10)
            lst.add((ligne*10) - 1)
            lst.add(((ligne+1)*10) - 1)
            lst.add((ligne+1)*10)
        }
        else {
            lst.add(((ligne-2)*10) + (col-1))
            lst.add(((ligne-2)*10) + col)
            lst.add(((ligne-2)*10) + (col+1))
            lst.add(((ligne-1)*10) + (col-1))
            lst.add(((ligne-1)*10) + (col+1))
            lst.add((ligne*10) + (col-1))
            lst.add((ligne*10) + col)
            lst.add((ligne*10) + (col+1))
        }
    }

    return lst
}


fun incAllOctos(mapOV:MutableMap<Int, Int>) : MutableMap<Int, Int> {

    var mapWork = mapOV

    for (m in mapWork) {
        val k = m.key
        val v = m.value
        if (v == 9)
            mapWork[k] = 0
        else
            mapWork[k] = v + 1
    }

    return mapWork
}


fun main() {

    var rep = 0L

    val vIn = readInput("input11")
    //val vIn = readInput("test11")

    var tabOctos: Array<Array<Int>> = Array (11) { Array(11) { 0 } }
    var tabValOctos: Array<Array<Int>> = Array (101) { Array(9) { -1 } }
    var mapOctos = mutableMapOf<Int, Int>()

    var cL = 0
    var cOctos = 0

    vIn.forEach {
        val s = it
        cL++
        var cC = 0
        for (el in s) {
            cC++
            cOctos++
            tabOctos[cL][cC] = el.code - 48
            mapOctos[cOctos] = el.code - 48
        }
    }

    var cp = 0
    cL = 0
    for (i in 1..10) {
        cL++
        var cC = 0
        for (j in 1..10) {
            cC++
            cp++
            var lstAdja = getAdjacents(cL, cC)
            lstAdja.forEachIndexed { idx, it ->
                tabValOctos[cp][idx+1] = it
            }
        }
    }

    for (i in 1..100) {

        mapOctos = incAllOctos(mapOctos)

        var cp = 0
        var isFlashed = mutableSetOf<Int>()

        while (true) {
            cp = 0
            var mapNeuf = mapOctos.filter { it.value == 0 }

            mapNeuf.forEach {
                var nO = it.key
                if (!isFlashed.contains(nO)) {
                    isFlashed.add(nO)
                    rep++
                    cp++

                    for (adj in tabValOctos[nO]) {
                        if (adj != -1) {
                            val x = mapOctos[adj]
                            if (x != null) {
                                if (x == 9) {
                                    mapOctos[adj] = 0
                                } else if (mapOctos[adj] != 0)
                                    mapOctos[adj] = x + 1
                            }
                        }
                    }
                }
            }

           if (cp == 0) break
        }
    }


    println(rep)
}



