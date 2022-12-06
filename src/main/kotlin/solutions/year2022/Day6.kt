package solutions.year2022

import DataFetcher
import solutions.Solution
import javax.lang.model.type.UnionType

class Day6(override var dataFetcher: DataFetcher) : Solution {
    override val year: Int = 2022
    override val day: Int = 6
    override lateinit var inputAsList: List<String>
    override lateinit var input: String

    init {
        getInputData()
    }

    override fun partOneResult(): String =
        inputAsList.sumOf { it.marker(4) }.toString()

    override fun partTwoResult(): String =
        inputAsList.sumOf { it.marker(14) }.toString()

    private fun String.marker(size: Int, index: Int = 0): Int =
        when (this.slice(index until size + index).toSet().count()) {
            size -> size + index
            else -> this.marker(size, index + 1)
        }
}