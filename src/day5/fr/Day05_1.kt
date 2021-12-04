package day5.fr

import java.io.File

fun readInputInt(name: String) = File("src", "$name.txt").readLines()


fun main() {

    val testInput = day2.fr.readInputInt("input5")

    val regex = "^(\\d+),(\\d+)\\s->\\s(\\d+),(\\d+)".toRegex()

    var x1 = 0
    var x2 = 0
    var y1 = 0
    var y2 = 0

    var maxX = 0
    var maxY = 0

    var lstH = mutableListOf<Triple<Int, Int, Int>>()
    var lstV = mutableListOf<Triple<Int, Int, Int>>()

    testInput.forEach {
        val match = regex.find(it)
        x1 = match!!.groups[1]!!.value.toInt()
        y1 = match.groups[2]!!.value.toInt()
        x2 = match.groups[3]!!.value.toInt()
        y2 = match.groups[4]!!.value.toInt()

        maxX = maxOf(maxX, x1)
        maxX = maxOf(maxX, x2)
        maxY = maxOf(maxY, y1)
        maxY = maxOf(maxY, y2)

        if (x1 == x2) {
            val yy1 = minOf(y1, y2)
            val yy2 = maxOf(y1, y2)
            val v = Triple(x1, yy1, yy2)
            lstV.add(v)
        }
        else if (y1 == y2) {
            val xx1 = minOf(x1, x2)
            val xx2 = maxOf(x1, x2)
            val h = Triple(y1, xx1, xx2)
            lstH.add(h)
        }
    }

    var tabVents = Array (maxY+1) { Array(maxX+1) { 0 } }

    lstH.forEach {
        for (i in it.second ..it.third) {
            tabVents[it.first][i]++
        }
    }

    lstV.forEach {
        for (i in it.second ..it.third) {
            tabVents[i][it.first]++
        }
    }

    var rep = 0
    tabVents.forEach {
        it.forEach { it2 ->
            if (it2 > 1)
                rep++
        }
    }

    println(rep)

}


