package solutions.year2022

import DataFetcher
import solutions.Solution

class Day3(override var dataFetcher: DataFetcher) : Solution {
    override val year: Int = 2022
    override val day: Int = 3
    override lateinit var inputAsList: List<String>
    override lateinit var input: String

    init {
        getInputData()
    }

    //implement first solution
    override fun partOneResult(): String {
        val foo = inputAsList.map { Pair(it.slice(0 until it.count() / 2), it.slice(it.count() / 2 until it.count())) }
        val foo2 =
            foo.map { it.first.first { firstType -> it.second.any { secondType -> firstType == secondType } } }
        val foo3 = foo2.map { it.code }
        val foo4 = foo3.map { if (it > 96) it - 96 else it - 64 + 26 }
        val foo5 = foo4.sum()
        return foo5.toString()
    }

    //implement second solution
    override fun partTwoResult(): String {
        val foo =
            (0 until inputAsList.count() / 3).map { inputAsList.filterIndexed { index, s -> ((index + 1)) > ((it + 1) * 3) - 3 && ((index + 1)) <= ((it + 1) * 3) } }
        val foo2 =
            foo.map { Pair(it[0].filter { firstType -> it[1].any { secondType -> firstType == secondType } }, it[2]) }
        val foo3 =
            foo2.map { it.first.first { firstType -> it.second.any { secondType -> firstType == secondType } }.code }
        val foo4 = foo3.map { if (it > 96) it - 96 else it - 64 + 26 }
        val foo5 = foo4.sum()

        return foo5.toString()
    }

    fun findType(it: Pair<String, String>): Char {
        try {

            return it.first.first { firstType -> it.second.any { secondType -> firstType == secondType } }
        } catch (e: Exception) {
            var error = it
        }
        return 'a'
    }
}