package solutions.year2022

import DataFetcher
import solutions.Solution

class Day4(override var dataFetcher: DataFetcher) : Solution {
    override val year: Int = 2022
    override val day: Int = 4
    override lateinit var inputAsList: List<String>
    override lateinit var input: String

    init {
        getInputData()
    }

    override fun partOneResult(): Int =
        inputAsList
            .toPairs()
            .map {
                Pair(it.first.toSection(), it.second.toSection())
                    .fullyContains()
            }
            .count { it }

    override fun partTwoResult(): Int =
        inputAsList
            .toPairs()
            .map {
                Pair(it.first.toSection(), it.second.toSection())
                    .contains()
            }
            .count { it }

    private fun List<String>.toPairs(): List<Pair<String, String>> {
        return this.map { it.split(",").let { parts -> Pair(parts[0], parts[1]) } }
    }

    private fun String.toSection(): Set<Int> =
        this.split("-").let { (it[0].toInt()..it[1].toInt()).map { n -> n } }.toSet()

    private fun Pair<Set<Int>, Set<Int>>.fullyContains(): Boolean =
        this.first.containsAll(this.second) || this.second.containsAll(this.first)

    private fun Pair<Set<Int>, Set<Int>>.contains(): Boolean =
        this.first.intersect(this.second).isNotEmpty()
}