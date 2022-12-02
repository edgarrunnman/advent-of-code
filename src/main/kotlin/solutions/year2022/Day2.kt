package solutions.year2022

import DataFetcher
import solutions.Solution

class Day2(override var dataFetcher: DataFetcher) : Solution {
    override val year: Int = 2022
    override val day: Int = 2
    override lateinit var inputAsList: List<String>
    override lateinit var input: String
    init {
        getInputData()
    }

    override fun partOneResult(): String =
        inputAsList.sumOf {
            ((it[2].code - it[0].code + 2) % 3) * 3 + (it[2].code - 87) }.toString()

    override fun partTwoResult(): String =
        inputAsList.sumOf {
            (it[2].code - 88) * 3 + ((it[2].code + it[0].code - 1 ) % 3) + 1 }.toString()
}