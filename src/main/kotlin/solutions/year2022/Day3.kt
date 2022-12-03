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

    override fun partOneResult(): String =
        inputAsList.sumOf { it.splitToPair().common().code.priority() }.toString()

    override fun partTwoResult(): String =
        inputAsList.groupToTriple().sumOf { it.common().code.priority() }.toString()

    private fun String.splitToPair(): Pair<String, String> =
        Pair(
            this.slice(0 until this.count() / 2),
            this.slice(this.count() / 2 until this.count())
        )

    private fun Pair<String, String>.common(): Char =
        this.first.first { it in this.second }

    private fun Triple<String, String, String>.common(): Char =
        this.first
            .filter { it in this.second }
            .first { it in this.third }

    private fun Int.priority(): Int =
        if (this > 96) this - 96 else this - 64 + 26

    private fun List<String>.groupToTriple(): List<Triple<String, String, String>> =
        (0 until this.count() / 3)
            .map {
                this.filterIndexed { n, _ ->
                    n + 1 > (it + 1) * 3 - 3 && n + 1 <= (it + 1) * 3
                }
            }
            .map { Triple(it[0], it[1], it[2]) }


}