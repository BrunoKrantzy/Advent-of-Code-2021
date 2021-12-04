package day5.fr

fun main() {

    val testInput = day2.fr.readInputInt("input5")
//    val testInput = day2.fr.readInputInt("test5")

    val regex = "^(\\d+),(\\d+)\\s->\\s(\\d+),(\\d+)".toRegex()

    var x1 = 0
    var x2 = 0
    var y1 = 0
    var y2 = 0

    var maxX = 0
    var maxY = 0

    var lstH = mutableListOf<Triple<Int, Int, Int>>()
    var lstV = mutableListOf<Triple<Int, Int, Int>>()

    var lstD = mutableListOf<Pair<Pair<Int, Int>, Pair<Int, Int>>>()

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
        else {
            val d: Pair<Pair<Int, Int>, Pair<Int, Int>>
            if (x1 < x2) {
                d = Pair(Pair(x1, y1), Pair(x2, y2))
            }
            else {
                d = Pair(Pair(x2, y2), Pair(x1, y1))
            }
            lstD.add(d)
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

    lstD.forEach {
        var dx1 = it.first.first
        var dx2 = it.second.first
        var dy1 = it.first.second
        var dy2 = it.second.second
        var difXY = dx2 - dx1

        for (i in 0..difXY) {
            if (dy1 < dy2) {
                tabVents[dy1+i][dx1+i]++
            }
            else {
                tabVents[dy1-i][dx1+i]++
            }
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




