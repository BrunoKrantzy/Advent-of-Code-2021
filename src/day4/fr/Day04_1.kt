package day4.fr

import java.io.File

fun readInput(name: String) = File("src", "$name.txt").readLines()

data class Carton(var mapNum: MutableMap<Int, Int> = mutableMapOf<Int, Int>()) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Carton

        if (!tabH.contentDeepEquals(other.tabH)) return false

        return true
    }
    override fun hashCode(): Int {
        return tabH.contentDeepHashCode()
    }

    var tabH:Array<Array<Int>> = Array (5) { Array(5) { 0 } }
    var tabV:Array<Array<Int>> = Array (5) { Array(5) { 0 } }
    var numero = 0
}


data class LiCol(var mapNum: MutableMap<Int, Int> = mutableMapOf<Int, Int>()) {
    var compteur = 0
    var numCarton = 0
    var tCL = Array(5) {-1}
}


fun main() {

    val fileIn = readInput("input4")

    var numbers = fileIn[0].split(',').map { it.toInt() }
    var lstCartons = mutableListOf<Carton>()

    var lstLC = mutableListOf<LiCol>()

    var no = 0

    for (i in 1 until fileIn.size step 6) {
        var tbH = Array (5) { Array(5) { 0 } }
        var tbV = Array (5) { Array(5) { 0 } }
        var mapN = mutableMapOf<Int, Int>()
        var carton = Carton(mapN)
        carton.numero = no++

        for (j in 1..5) {
            var li = fileIn[i+j]
            li = li.trim()
            var lstLi = li.split("   ", "  ", " ").map { it.toInt() }
            lstLi.forEachIndexed { index, s ->
                tbH[j-1][index] = s
                tbV[index][j-1] = s
                carton.mapNum[s] = 1
            }
        }
        carton.tabH = tbH
        carton.tabV = tbV
        lstCartons.add(carton)

        tbH.forEach {
            var liCol = LiCol(mapN)
            var mapLC = mutableMapOf<Int, Int>()
            it.forEach { it2 ->
                mapLC[it2] = 1
            }
            liCol.tCL = it
            liCol.mapNum = mapLC
            liCol.numCarton = carton.numero
            lstLC.add(liCol)
        }

        tbV.forEach {
            var liCol = LiCol(mapN)
            var mapLC = mutableMapOf<Int, Int>()
            it.forEach { it2 ->
                mapLC[it2] = 1
            }
            liCol.tCL = it
            liCol.mapNum = mapLC
            liCol.numCarton = carton.numero
            lstLC.add(liCol)
        }
    }

    var lastNum = 0
    var sumCarton = 0
    var nCarton = 0
    var end = false

    for (i in numbers.indices) {
        var n = numbers[i]
        lastNum = n
        for (it in lstLC) {
            if (it.mapNum.containsKey(n)) {
                it.mapNum[n] = 100
                it.compteur++
                if (it.compteur == 5) {
                    nCarton = it.numCarton
                    end = true
                    break
                }
            }
        }
        if (end) break
    }

    for (item in lstLC) {
        if (item.numCarton == nCarton) {
            item.tCL.forEach { it2 ->
                if (item.mapNum.containsKey(it2)) {
                    if (item.mapNum[it2] != 100)
                        sumCarton += it2
                }
            }
        }
    }


    println(lastNum * (sumCarton/2))
}

