package day14.fr

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

    val vTemplate = day11.fr.readInput("test14a")
    val vRules = day11.fr.readInput("test14b")
    //val vTemplate = day11.fr.readInput("input14a")
    //val vRules = day11.fr.readInput("input14b")

    var sTemplate = StringBuilder()
    sTemplate.append(vTemplate[0])

    var regex = "^([A-Z]{2})\\s->\\s([A-Z])".toRegex()

    var mapRules = mutableMapOf<String, String>()

    vRules.forEach {
        val match = regex.find(it)
        val rDuo = match!!.groups[1]!!.value
        val rIns = match.groups[2]!!.value
        mapRules[rDuo] = rIns
    }

    var nbSteps = 10
    for (i in 1..nbSteps) {
        var strT = sTemplate.toString()
        sTemplate.clear()
        var l1 = ""
        var l2 = ""
        for (let in 0 until strT.length - 1) {
            l1 = strT[let].toString()
            l2 = strT[let+1].toString()
            var duo:String = l1 + l2
            var cIn = mapRules[duo]
            if (cIn != null) {
                sTemplate.append("$l1$cIn")
            }
        }
        sTemplate.append(l2)
    }

    var strFini = sTemplate.toString()
    var lgStr = strFini.length
    val frequencies = strFini.groupingBy { it }.eachCount()

    var vMax = Long.MIN_VALUE
    var vMin = Long.MAX_VALUE

    frequencies.forEach {
        var v = it.value.toLong()
        vMax = maxOf(vMax, v)
        vMin = minOf(vMin, v)
    }

    rep = vMax - vMin

    println(rep)
}


