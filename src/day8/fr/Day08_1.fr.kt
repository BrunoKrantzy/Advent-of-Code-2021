package day8.fr

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

    //val vIn = readInput("test8")
    val vIn = readInput("input8")

    // val regex = "^([a-g]{1,7})\\s([a-g]{1,7})\\s([a-g]{1,7})\\s([a-g]{1,7})\\s([a-g]{1,7})\\s([a-g]{1,7})
    // \\s([a-g]{1,7})\\s([a-g]{1,7})\\s([a-g]{1,7})\\s([a-g]{1,7})\\s\\|\\s([a-g]{1,7})\\s([a-g]{1,7})\\s
    // ([a-g]{1,7})\\s([a-g]{1,7})\$".toRegex()
    val regex = "^\\s|([a-g]{1,7})\\s([a-g]{1,7})\\s([a-g]{1,7})\\s([a-g]{1,7})\$".toRegex()

    var lstP = mutableListOf<String>()
    vIn.forEach {
        val match = regex.find(it)
        val s1 = match!!.groups[1]!!.value
        lstP.add(s1)
        val s2 = match.groups[2]!!.value
        lstP.add(s2)
        val s3 = match.groups[3]!!.value
        lstP.add(s3)
        val s4 = match.groups[4]!!.value
        lstP.add(s4)

        lstP.forEach { item ->
            when (item.length) {
                2 -> rep++
                4 -> rep++
                3 -> rep++
                7 -> rep++
            }
        }
        lstP.clear()

    }

    println(rep)
}


