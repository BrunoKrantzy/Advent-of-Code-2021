package day21.fr

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


fun main(args: Array<String>) {

    val inLines = readInput("input21")

    var setXYZon = mutableSetOf<Triple<Long, Long, Long>>()

    var regex = "^(on|off)\\sx=(-?\\d+)..(-?\\d+),y=(-?\\d+)..(-?\\d+),z=(-?\\d+)..(-?\\d+)".toRegex()

    inLines.forEach {
        val match = regex.find(it)
        val vPos = match!!.groups[1]!!.value
        val x1 = match!!.groups[2]!!.value.toLong()
        val x2 = match!!.groups[3]!!.value.toLong()
        val y1 = match!!.groups[4]!!.value.toLong()
        val y2 = match!!.groups[5]!!.value.toLong()
        val z1 = match!!.groups[6]!!.value.toLong()
        val z2 = match!!.groups[7]!!.value.toLong()

        if (x1 in -50..50 && x2 in -50..50 && y1 in -50..50 && y2 in -50..50  && z1 in -50..50 && z2 in -50..50) {
            for (x in x1..x2) {
                for (y in y1..y2) {
                    for (z in z1..z2) {
                        var tT = Triple(x, y, z)
                        when (vPos) {
                            "on" -> {
                                setXYZon.add(tT)
                            }
                            "off" -> {
                                if (setXYZon.contains(tT))
                                    setXYZon.remove(tT)
                            }
                        }
                    }
                }
            }
        }
    }

    var cp = 0L
    setXYZon.forEach {
        cp++
    }

    println(cp)
}


