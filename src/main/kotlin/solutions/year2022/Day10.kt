package solutions.year2022

import DataFetcher
import solutions.Solution

class Day10(override var dataFetcher: DataFetcher) : Solution {
    override val year: Int = 2022
    override val day: Int = 10
    override lateinit var inputAsList: List<String>
    override lateinit var input: String

    init {
        getInputData()
    }

    override fun partOneResult(): String {
        var foo = inputAsList
        return foo.toString()
    }

    override fun partTwoResult(): String {
        var foo = inputAsList
        return foo.toString()
    }
}


