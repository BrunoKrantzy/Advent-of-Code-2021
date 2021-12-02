
package day2.fr


fun main() {

    val testInput = readInputInt("input2")

    val regex = "^([a-z]+)\\s(\\d+)".toRegex()

    var posH = 0L
    var depth = 0L
    var aim = 0L

    testInput.forEach {
        val match = regex.find(it)
        val com = match!!.groups[1]!!.value
        val param = match.groups[2]!!.value.toLong()

        when (com) {
            "forward" -> {
                posH += param
                depth += (aim * param)
            }

            "down" -> {
                aim += param
            }

            "up" -> {
                aim -= param
            }
        }
    }


    println(posH * depth)

}


