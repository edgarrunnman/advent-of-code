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

    override fun partOneResult(): String =
        inputAsList
            .generateCycles()
            .mapIndexed() { index, cycle ->
                Pair(((index + 20) / 40) * 40 + 20, cycle)
            }
            .groupBy { it.first }
            .map { it.value.last() }
            .slice(0 until 6)
            .sumOf { it.first * it.second }.toString()

    override fun partTwoResult(): String =
        inputAsList
            .generateCycles()
            .mapIndexed() { index, cycle ->
                Pair(index, cycle)
            }
            .groupBy { it.first / 40 }
            .map {
                it.value.map { sprt ->
                    if (sprt.first % 40 in (sprt.second - 1..sprt.second + 1)) "#" else "."
                }.joinToString("")
            }
            .slice(0 until 6)
            .joinToString("\n")

    private fun List<String>.generateCycles(): List<Int> =
        this.fold(listOf(1)) { cycles, cmd ->
            cmd.executeCmd(cycles)
        }

    private fun String.executeCmd(cycles: List<Int>): List<Int> =
        when {
            this.startsWith("noop") -> cycles + cycles.last()
            else -> cycles + listOf(cycles.last(), cycles.last() + this.split(" ")[1].toInt())
        }

}


