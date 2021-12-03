package day3.fr

fun main() {

    val testInput = readInput("input3")

    val gamma = StringBuilder()
    val epsilon = StringBuilder()

    var gamInt = 0L
    var epsInt = 0L

    val tab0 = intArrayOf(0, 0, 0, 0 ,0, 0, 0, 0, 0, 0 ,0, 0, 0)
    val tab1 = intArrayOf(0, 0, 0, 0 ,0, 0, 0, 0, 0, 0 ,0, 0, 0)

    testInput.forEach {

        for (i in 1..it.length) {
            if (it[i-1] == '1') {
                tab1[i]++
            }
            else {
                tab0[i]++
            }
        }
    }

    for (i in 1..12) {
        if (tab1[i] > tab0[i]) {
            gamma.append("1")
            epsilon.append("0")
        }
        else {
            gamma.append("0")
            epsilon.append("1")
        }
    }

    gamInt = gamma.toString().toLong(2)
    epsInt = epsilon.toString().toLong(2)


    println(gamInt * epsInt)

}



