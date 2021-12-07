package day6.fr

import java.io.*

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


//fun readInput(name: String) = File("src", "$name.txt").readLines()


fun main() {

    //val vIn = readInput("input6")
    val vIn = readInput("test66")

    var lstFish = vIn[0].split(',').map { it.toInt() } as MutableList
    var lstWork = mutableListOf<Int>()

    var rep = 0L
    val nJours = 200

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


    val path1 = System.getProperty("user.dir") + "\\src\\f1.txt"
    val path2 = System.getProperty("user.dir") + "\\src\\f2.txt"

    var fw: FileWriter
    var fr: FileReader
    var fInput = 1

    fw = FileWriter(path1, true)
    lstFish.forEach {
        fw.write(it.toString())
    }
    fw.close()


    for (i in 1..56) {

        if (fInput == 1) {
            fr = FileReader(path1)
            fw = FileWriter(path2, true)
            fInput = 0
        }
        else {
            fr = FileReader(path2)
            fw = FileWriter(path1, true)
            fInput = 1
        }

        var c = 0
        while (fr.ready())  {
            c = fr.read()
            when (c) {
                48 -> {
                    fw.write(54)
                    fw.write(56)
                }
                else  -> {
                    fw.write(c - 1)
                }
            }
        }

    fr.close()
    fw.close()

    }

}





