package solutions.year2021

import DataFetcher
import solutions.Solution

class Day1(override var dataFetcher: DataFetcher) : Solution {
    override val year: Int = 2021
    override val day: Int = 1
    override lateinit var puzzleInput: List<String>

    init {
        getInputData()
    }

    override fun solution1(): String =
        puzzleInput.map { it.toInt() }.incrementCounter().toString()

    override fun solution2(): String =
        puzzleInput.map { it.toInt() }.sumByThree().incrementCounter().toString()

    private fun List<Int>.sumByThree(): List<Int> =
        (1..this.size - 3)
            .fold<Int, List<Int>>(mutableListOf(0)) { sumsOfTree, n ->
                sumsOfTree.plus(this.slice(n..n + 2).sum())
            }

    private fun List<Int>.incrementCounter(): Int =
        this.slice(1 until this.size)
            .foldIndexed(0) { n, count, reading ->
                if (this[n] < reading) count + 1 else count
            }
}

