package solutions.year2022

import DataFetcher
import solutions.Solution

class Day1(override var dataFetcher: DataFetcher) : Solution {
    override val year: Int = 2022
    override val day: Int = 1
    override lateinit var puzzleInput: List<String>

    init {
        getInputData(true)
    }

    override fun partOneResult(): String =
        puzzleInput.totalPerUnit().first().toString()

    override fun partTwoResult(): String =
        puzzleInput.totalPerUnit().subList(0, 3).sum().toString()
    private fun List<String>.totalPerUnit(): List<Int> =
        this.joinToString(",") { if (it == "") ":" else it }
            .split(':')
            .map { it.split(',')
                .filter { c -> c != "" }
                .sumOf { c -> c.toInt() } }
            .sortedDescending()
}