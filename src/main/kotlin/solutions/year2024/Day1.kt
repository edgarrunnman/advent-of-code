package solutions.year2024

import DataFetcher
import solutions.Solution

class Day1(override var dataFetcher: DataFetcher) : Solution {
    override val year: Int = 2024
    override val day: Int = 1
    override lateinit var inputAsList: List<String>
    override lateinit var input: String

    init {
        getInputData()
    }

    override fun partOneResult(): String =
        normalizedInput()
            .let { (left, right) ->
                left.map { it.toInt() }.sortedBy { id -> id } to
                        right.map { it.toInt() }.sortedBy { id -> id }
            }.let { (left, right) ->
                left.zip(right)
            }.sumOf { (left, right) -> Math.abs(left - right) }.toString()

    override fun partTwoResult(): String =
        normalizedInput()
            .let { (left, right) ->
                left.map { it.toInt() } to
                        right.groupBy { it }.map { pair -> pair.key.toInt() to pair.value.size }.toMap()
            }.let { (left, lookup) ->
                left.sumOf { it * lookup.getOrDefault(it, 0) }
            }.toString()

    private fun normalizedInput() =
        inputAsList.map { line -> line.trim().split("   ").let { ids -> ids[0] to ids[1] } }.splitPairs()

    private fun List<Pair<String, String>>.splitPairs() =
        this.let { pairs -> pairs.map { pair -> pair.first } to pairs.map { pair -> pair.second } }
}
