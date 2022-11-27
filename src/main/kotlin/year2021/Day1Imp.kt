package year2021

import DataFetcherImp
import Solution

class Day1Imp: Solution {
    private var fetcher = DataFetcherImp(2021, 1)
    private var data: List<String> = fetcher.getData()

    override fun solution1(): String =
        this.data
            .map { it.toInt() }
            .let { incrementCounter(it) }
            .toString()

    override fun solution2(): String =
        this.data
            .map { it.toInt() }
            .let { sumByThree(it) }
            .let { incrementCounter(it) }
            .toString()

    private fun sumByThree(input: List<Int>): List<Int> =
        (1..input.size - 3)
            .fold<Int, List<Int>>(mutableListOf(0)) { sumsOfTree, n ->
                sumsOfTree.plus(input.slice(n..n + 2).sum())
            }

    private fun incrementCounter(input: List<Int>): Int =
        input.slice(1 until input.size)
            .foldIndexed(0) { n, count, reading ->
                if (input[n] < reading) count + 1 else count
            }
}