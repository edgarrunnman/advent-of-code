package solutions.year2021

import DataFetcher
import solutions.Solution

class Day5(override var dataFetcher: DataFetcher) : Solution {
    override val year: Int = 2021
    override val day: Int = 5
    override lateinit var inputAsList: List<String>
    override lateinit var input: String

    init {
        getInputData()
    }

    //implement first solution
    override fun partOneResult(): String {
        var sett: Map<Pair<Int, Int>, Int> = mutableMapOf()
        var count = 0
        inputAsList.forEach { sett.plus(Pair(Pair(1, 1), sett[Pair(1, 1)].val))
        }

        return ""
    }

    //implement second solution
    override fun partTwoResult(): String = "not rdy"
}