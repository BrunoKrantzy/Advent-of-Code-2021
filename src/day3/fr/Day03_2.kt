package day3.fr

fun main() {

    val testInput = readInput("input3")
    var lstWork = testInput.toMutableList()

    var tab0 = intArrayOf(0, 0, 0, 0 ,0, 0, 0, 0, 0, 0 ,0, 0, 0)
    var tab1 = intArrayOf(0, 0, 0, 0 ,0, 0, 0, 0, 0, 0 ,0, 0, 0)

    var oxy = 0
    var co2 = 0

    fun compter(lst:List<String>) {
        tab0 = intArrayOf(0, 0, 0, 0 ,0, 0, 0, 0, 0, 0 ,0, 0, 0)
        tab1 = intArrayOf(0, 0, 0, 0 ,0, 0, 0, 0, 0, 0 ,0, 0, 0)
        lst.forEach {
            for (i in 1..it.length) {
                if (it[i-1] == '1') {
                    tab1[i]++
                }
                else {
                    tab0[i]++
                }
            }
        }
    }

    // Oxy
    compter(testInput)

    while (lstWork.size > 1) {
        val liste = lstWork.toMutableList()
        for (bit in 1..12) {
            var v = ' '
            if (tab1[bit] >= tab0[bit]) {
                v = '0'
            }
            else {
                v = '1'
            }

            liste.removeAll { it[bit-1] == v }
            lstWork = liste.toMutableList()

            // recompter
            compter(lstWork)

            if (lstWork.size == 1)
                break
        }
    }

    oxy = lstWork[0].toInt(2)

    // CO2
    compter(testInput)
    lstWork = testInput.toMutableList()

    while (lstWork.size > 1) {
        val liste = lstWork.toMutableList()
        for (bit in 1..12) {
            var v = ' '
            if (tab0[bit] <= tab1[bit]) {
                v = '1'
            }
            else {
                v = '0'
            }

            liste.removeAll { it[bit-1] == v }
            lstWork = liste.toMutableList()

            // recompter
            compter(lstWork)

            if (lstWork.size == 1)
                break
        }
    }

    co2 = lstWork[0].toInt(2)


    println(oxy * co2)
}


