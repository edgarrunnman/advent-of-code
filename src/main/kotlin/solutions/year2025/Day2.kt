package solutions.year2025

import DataFetcher
import solutions.Solution

class Day2(override var dataFetcher: DataFetcher) : Solution {
    override val year: Int = 2025
    override val day: Int = 2
    override lateinit var inputAsList: List<String>
    override lateinit var input: String

    init {
        getInputData()
    }

    override fun partOneResult(): String =
        input.commaSeparated()
            .flatMap { idPair ->
                idPair
                    .rangeFromPair()
                    .filter {
                        it.toString()
                            .invalidWithPatternOfTwo() }
            }.sum().toString()


    override fun partTwoResult(): String =
        input.commaSeparated()
            .flatMap { idPair ->
                idPair
                    .rangeFromPair()
                    .filter {
                        it.toString()
                            .invalidWithPatternOfMany() }
            }.sum().toString()

    private fun String.commaSeparated() = this.split(",")

    private fun String.rangeFromPair() = LongRange(this.split("-")[0].toLong(), this.split("-")[1].toLong())

    private fun String.invalidWithPatternOfTwo() =
        this.substring(0 until this.length / 2) == this.substring(this.length / 2)

    private fun String.invalidWithPatternOfMany() =
        (1 until this.length).any { this.chunked(it).distinct().size == 1 }

}