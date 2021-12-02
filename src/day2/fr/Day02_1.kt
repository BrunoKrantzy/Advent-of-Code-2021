
package day2.fr

import java.io.File

fun readInputInt(name: String) = File("src", "$name.txt").readLines()

fun main() {

    val testInput = readInputInt("input2")

    val regex = "^([a-z]+)\\s(\\d+)".toRegex()

    var posH = 0
    var depth = 0

    testInput.forEach {
        val match = regex.find(it)
        val com = match!!.groups[1]!!.value
        val param = match.groups[2]!!.value.toInt()

        when (com) {
            "forward" -> {
                posH += param
            }

            "down" -> {
                depth += param
            }

            "up" -> {
                depth -= param
            }
        }
    }


    println(posH * depth)

}


