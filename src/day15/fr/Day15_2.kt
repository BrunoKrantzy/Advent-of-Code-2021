package day15.fr

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


fun incRem(str:String) : String {
    val strRet = StringBuilder()
    for (j in str.indices) {
        var n = str[j].code - 48
        if (n + 1 > 9) n = 1
        else n += 1

        strRet.append(n.toString())
    }

    return strRet.toString()
}


fun traiteListe(lst:MutableList<String>) : MutableList<String> {
    var liste = lst
    for (nLig in 0 until liste.size) {
        val vLinc = incRem(liste[nLig])
        liste[nLig] = vLinc
    }

    return liste
}



fun main() {

    var rep = 0L

    var vInGrille = day11.fr.readInput("test15") as MutableList
    //var vInGrille = day11.fr.readInput("input15") as MutableList

    val vH = vInGrille.size
    val vL = vInGrille[0].length

    val vTabGrille = Array (vH * vH) { Array(vL * vH) { -1 } }

    var lstDesListes = mutableListOf<MutableList<String>>()
    lstDesListes.add(vInGrille)
    var lstWork = vInGrille.toMutableList()
    for (l in 2..vH * 2) {
        var newListe = traiteListe(lstWork)
        lstDesListes.add(newListe)
        lstWork = newListe.toMutableList()
    }

    var lGlob = 0
    var cGlob = 0
    var offset = 0

    for (nLst in 0 .. 4) {
        for (ligne in 0 until vH) {

            for (colonne in 0 until vL) {

                val lstTraitee = lstDesListes[colonne + offset]
                var lT = lstTraitee[ligne]
                var lstCol = mutableListOf<Int>()
                lT.forEach {
                    lstCol.add(it.code - 48)
                }

                for (col in 0 until vL) {
                    vTabGrille[lGlob][cGlob] = lstCol[col]
                    cGlob++
                }
            }
            cGlob = 0
            lGlob++
        }
        offset++
    }


    val lstVertex = mutableListOf<Triple<Int, Int, Int>>()

    var nNode = 0

    // lecture tableau
    for (lig in 0 until (vH * 5)) {
        for (col in 0 until (vL * 5)) {
            nNode = col
            if (lig == 0) {
                if (col == 0) {
                    lstVertex.add(Triple(nNode, nNode+1, vTabGrille[lig][col+1]))
                    lstVertex.add(Triple(nNode, nNode+(vL * 5), vTabGrille[lig+1][col]))
                }
                else if (col == (vL * 5) - 1) {
                    lstVertex.add(Triple(nNode, nNode-1, vTabGrille[lig][col-1]))
                    lstVertex.add(Triple(nNode, nNode+(vL * 5), vTabGrille[lig+1][col]))
                }
                else {
                    lstVertex.add(Triple(nNode, nNode-1, vTabGrille[lig][col-1]))
                    lstVertex.add(Triple(nNode, nNode+1, vTabGrille[lig][col+1]))
                    lstVertex.add(Triple(nNode, nNode+(vL * 5), vTabGrille[lig+1][col]))
                }
            }

            else if (lig == (vH * 5) - 1) {
                nNode = col + ((vL * 5) * (lig))
                if (col == 0) {
                    lstVertex.add(Triple(nNode, nNode+1, vTabGrille[lig][col+1]))
                    lstVertex.add(Triple(nNode, nNode-(vL * 5), vTabGrille[lig-1][col]))
                }
                else if (col == (vL * 5) - 1) {
                    lstVertex.add(Triple(nNode, nNode-1, vTabGrille[lig][col-1]))
                    lstVertex.add(Triple(nNode, nNode-(vL * 5), vTabGrille[lig-1][col]))
                }
                else {
                    lstVertex.add(Triple(nNode, nNode-1, vTabGrille[lig][col-1]))
                    lstVertex.add(Triple(nNode, nNode+1, vTabGrille[lig][col+1]))
                    lstVertex.add(Triple(nNode, nNode-(vL * 5), vTabGrille[lig-1][col]))
                }
            }

            else { // lignes intérieures
                nNode = col + ((vL * 5) * (lig))
                if (col == 0) {
                    lstVertex.add(Triple(nNode, nNode-(vL * 5), vTabGrille[lig-1][col]))
                    lstVertex.add(Triple(nNode, nNode+1, vTabGrille[lig][col+1]))
                    lstVertex.add(Triple(nNode, nNode+(vL * 5), vTabGrille[lig+1][col]))
                }
                else if (col == (vL * 5) - 1) {
                    lstVertex.add(Triple(nNode, nNode-(vL * 5), vTabGrille[lig-1][col]))
                    lstVertex.add(Triple(nNode, nNode-1, vTabGrille[lig][col-1]))
                    lstVertex.add(Triple(nNode, nNode+(vL * 5), vTabGrille[lig+1][col]))
                }
                else {
                    lstVertex.add(Triple(nNode, nNode-(vL * 5), vTabGrille[lig-1][col]))
                    lstVertex.add(Triple(nNode, nNode+1, vTabGrille[lig][col+1]))
                    lstVertex.add(Triple(nNode, nNode-1, vTabGrille[lig][col-1]))
                    lstVertex.add(Triple(nNode, nNode+(vL * 5), vTabGrille[lig+1][col]))
                }
            }
        }
    }


    val nbVertex = lstVertex.size

    val nbS = (vH * 5) * (vL * 5)
    val source = 0

    val graph: Array<IntArray> = Array (nbS) { IntArray(nbS) { 0 } }

    for (v in lstVertex) {
        val s1 = v.first
        val s2 = v.second
        val pV = v.third

        graph[s1][s2] = pV
    }

    val obShortPath = ShortestPath(nbS)
    val taBDist = obShortPath.dijkstra(graph, source)


    obShortPath.printSolution(taBDist)

}


