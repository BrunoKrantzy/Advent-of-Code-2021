import java.io.File

fun readInputInt(name: String) = File("src", "$name.txt").readLines().map { it.toInt() }

fun main() {

    val testInput = readInputInt("input")

    var base = Int.MAX_VALUE
    var c = 0

    val wList = testInput.windowed(3, 1, false)
    wList.forEach {
        val suite = it.sum()
        if (suite > base) c++
        base = suite
    }

    println(c)
}


