package solutions.year2022

import DataFetcher
import solutions.Solution

class Day1(override var dataFetcher: DataFetcher) : Solution {
    override val year: Int = 2022
    override val day: Int = 1
    override lateinit var inputAsList: List<String>
    override lateinit var input: String

    init {
        getInputData()
    }

    override fun partOneResult(): String =
        input.totalPerUnit().first().toString()

    override fun partTwoResult(): String =
        input.totalPerUnit().subList(0, 3).sum().toString()

    private fun String.totalPerUnit(): List<Int> =
        this.split("\n\n")
            .map { it.split("\n").filter { line -> line.isNotBlank() }.sumOf { c -> c.toInt() } }
            .sortedDescending()
}