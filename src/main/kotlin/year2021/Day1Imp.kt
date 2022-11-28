package year2021

import DataFetcherImp
import Solution

class Day1Imp : Solution {
    private var fetcher = DataFetcherImp(2021, 1)
    private var data: List<String> = fetcher.getData()

    override fun solution1(): String =
        this.data.map { it.toInt() }.incrementCounter().toString()

    override fun solution2(): String =
        this.data.map { it.toInt() }.sumByThree().incrementCounter().toString()

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

