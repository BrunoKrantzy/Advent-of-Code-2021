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


fun readInput(name: String) = File("src", "$name.txt").readLines()


class ShortestPath (nbV: Int) {

    private val nVertex = nbV

    private fun minDistance(dist: IntArray, sptSet: Array<Boolean?>): Int {
        // Initialize min value
        var min = Int.MAX_VALUE
        var minIndex = -1
        for (v in 0 until nVertex) if (sptSet[v] == false && dist[v] <= min) {
            min = dist[v]
            minIndex = v
        }
        return minIndex
    }

    // A utility function to print the constructed distance array
    fun printSolution(dist: IntArray) {
        println("Vertex \t\t Distance from Source")
        for (i in 0 until nVertex) println(i.toString() + " \t\t " + dist[i])
    }

    // Function that implements Dijkstra's single source shortest path
    // algorithm for a graph represented using adjacency matrix representation
    fun dijkstra(graph: Array<IntArray>, src: Int) : IntArray {
        val dist = IntArray(nVertex)
        // The output array. dist[i] will hold the shortest distance from src to i

        // sptSet[i] will true if vertex i is included in shortest
        // path tree or shortest distance from src to i is finalized
        val sptSet = arrayOfNulls<Boolean>(nVertex)

        // Initialize all distances as INFINITE and stpSet[] as false
        for (i in 0 until nVertex) {
            dist[i] = Int.MAX_VALUE
            sptSet[i] = false
        }

        // Distance of source vertex from itself is always 0
        dist[src] = 0

        // Find shortest path for all vertices
        for (count in 0 until nVertex - 1) {
            // Pick the minimum distance vertex from the set of vertices
            // not yet processed. u is always equal to src in first iteration.
            val u = minDistance(dist, sptSet)

            // Mark the picked vertex as processed
            sptSet[u] = true

            // Update dist value of the adjacent vertices of the picked vertex.
            for (v in 0 until nVertex)
            // Update dist[v] only if is not in sptSet, there is an edge from u to v, and total
            // weight of path from src to v through u is smaller than current value of dist[v]
                if (!sptSet[v]!! && graph[u][v] != 0 && dist[u] != Int.MAX_VALUE && dist[u] + graph[u][v] < dist[v])
                    dist[v] = dist[u] + graph[u][v]
        }

        // return the constructed distance array
        return dist
    }
}




fun main() {

    var rep = 0L

    //val vInGrille = day11.fr.readInput("test15")
    val vInGrille = day11.fr.readInput("input15")

    val vH = vInGrille.size
    val vL = vInGrille[0].length

    val vTabGrille = Array (vH) { Array(vL) { -1 } }

    // chargement tableau
    for (i in 0 until vH) {
        val vLi = vInGrille[i]
        for (j in vLi.indices) {
            vTabGrille[i][j] = vLi[j].code - 48
        }
    }


    var lstVertex = mutableListOf<Triple<Int, Int, Int>>()

    var nNode = 0

    // lecture tableau
    for (lig in 0 until vH) {
        for (col in 0 until vL) {
            nNode = col
            if (lig == 0) {
                if (col == 0) {
                    lstVertex.add(Triple(nNode, nNode+1, vTabGrille[lig][col+1]))
                    lstVertex.add(Triple(nNode, nNode+vL, vTabGrille[lig+1][col]))
                }
                else if (col == vL-1) {
                    lstVertex.add(Triple(nNode, nNode-1, vTabGrille[lig][col-1]))
                    lstVertex.add(Triple(nNode, nNode+vL, vTabGrille[lig+1][col]))
                }
                else {
                    lstVertex.add(Triple(nNode, nNode-1, vTabGrille[lig][col-1]))
                    lstVertex.add(Triple(nNode, nNode+1, vTabGrille[lig][col+1]))
                    lstVertex.add(Triple(nNode, nNode+vL, vTabGrille[lig+1][col]))
                }
            }

            else if (lig == vH - 1) {
                nNode = col + (vL * (lig))
                if (col == 0) {
                    lstVertex.add(Triple(nNode, nNode+1, vTabGrille[lig][col+1]))
                    lstVertex.add(Triple(nNode, nNode-vL, vTabGrille[lig-1][col]))
                }
                else if (col == vL - 1) {
                    lstVertex.add(Triple(nNode, nNode-1, vTabGrille[lig][col-1]))
                    lstVertex.add(Triple(nNode, nNode-vL, vTabGrille[lig-1][col]))
                }
                else {
                    lstVertex.add(Triple(nNode, nNode-1, vTabGrille[lig][col-1]))
                    lstVertex.add(Triple(nNode, nNode+1, vTabGrille[lig][col+1]))
                    lstVertex.add(Triple(nNode, nNode-vL, vTabGrille[lig-1][col]))
                }
            }

            else { // lignes intérieures
                nNode = col + (vL * (lig))
                if (col == 0) {
                    lstVertex.add(Triple(nNode, nNode-vL, vTabGrille[lig-1][col]))
                    lstVertex.add(Triple(nNode, nNode+1, vTabGrille[lig][col+1]))
                    lstVertex.add(Triple(nNode, nNode+vL, vTabGrille[lig+1][col]))
                }
                else if (col == vL - 1) {
                    lstVertex.add(Triple(nNode, nNode-vL, vTabGrille[lig-1][col]))
                    lstVertex.add(Triple(nNode, nNode-1, vTabGrille[lig][col-1]))
                    lstVertex.add(Triple(nNode, nNode+vL, vTabGrille[lig+1][col]))
                }
                else {
                    lstVertex.add(Triple(nNode, nNode-vL, vTabGrille[lig-1][col]))
                    lstVertex.add(Triple(nNode, nNode+1, vTabGrille[lig][col+1]))
                    lstVertex.add(Triple(nNode, nNode-1, vTabGrille[lig][col-1]))
                    lstVertex.add(Triple(nNode, nNode+vL, vTabGrille[lig+1][col]))
                }
            }
        }
    }


    val nbVertex = lstVertex.size

    val nbS = vH * vL
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



