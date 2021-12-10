
package day10.fr

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


fun main() {

    var rep = 0L

    var pileCh = MutableStack<Char>()

    val vIn = readInput("input10")
    //val vIn = readInput("test10")
    val vOut = mutableListOf<String>()

    vIn.forEach {
        var isG = true
        for (element in it) {
            when (element) {
                '(' -> pileCh.push(element)
                ')' -> {
                    val v = pileCh.peek()
                    if (v == '(')
                        pileCh.pop()
                    else {
                        isG = false
                        break
                    }
                }
                '[' -> pileCh.push(element)
                ']' -> {
                    val v = pileCh.peek()
                    if (v == '[')
                        pileCh.pop()
                    else {
                        isG = false
                        break
                    }
                }
                '{' -> pileCh.push(element)
                '}' -> {
                    val v = pileCh.peek()
                    if (v == '{')
                        pileCh.pop()
                    else {
                        isG = false
                        break
                    }
                }
                '<' -> pileCh.push(element)
                '>' -> {
                    val v = pileCh.peek()
                    if (v == '<')
                        pileCh.pop()
                    else {
                        isG = false
                        break
                    }
                }
            }
        }
        if (isG) vOut.add(it)
    }


    pileCh = MutableStack<Char>()

    var lstScores = mutableListOf<Long>()

    vOut.forEach {
        for (el in it) {
            if (el == '(' || el == '[' || el == '{' || el == '<' )
                pileCh.push(el)
            else
                pileCh.pop()
        }

        while (!pileCh.isEmpty()) {
            var c = pileCh.peek()
            when (c) {
                '(' -> rep = (rep * 5) + 1
                '[' -> rep = (rep * 5) + 2
                '{' -> rep = (rep * 5) + 3
                '<' -> rep = (rep * 5) + 4
            }
            pileCh.pop()
        }

        lstScores.add(rep)
        rep = 0L
    }

    lstScores.sort()
    val posScore = lstScores.size / 2

    println(lstScores[posScore])
}


