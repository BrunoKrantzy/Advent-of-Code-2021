fun main() {

    val testInput = readInput("input")

    var base = testInput[0].toInt()
    var c = 0
    for (i in 1 .. testInput.size - 1) {
        val suite = testInput[i].toInt()
        if (suite > base) c++
        base = suite
    }

    println(c)
}


