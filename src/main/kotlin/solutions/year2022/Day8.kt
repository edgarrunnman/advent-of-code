package solutions.year2022

import DataFetcher
import solutions.Solution

class Day8(override var dataFetcher: DataFetcher) : Solution {
    override val year: Int = 2022
    override val day: Int = 8
    override lateinit var inputAsList: List<String>
    override lateinit var input: String

    init {
        getInputData()
    }

    override fun partOneResult(): String =
        inputAsList
            .map { it.toList().map { t -> t.code } }
            .mapByCondition(::getVisibility)
            .count { it }.toString()

    override fun partTwoResult(): String =
        inputAsList
            .map { it.toList().map { t -> t.code } }
            .mapByCondition(::getScenicScore)
            .max().toString()

    private fun <T> List<List<Int>>.mapByCondition(function: (List<List<Int>>, Int) -> T): List<T> =
        this.mapIndexed { x, line ->
            line.mapIndexed() { y, point ->
                function(this.getDirections(Pair(x, y)), point)
            }
        }
            .flatMap { it.asIterable() }

    private fun getVisibility(lines: List<List<Int>>, height: Int): Boolean =
        lines.any { it.count { point -> point == height } == 1 && it.max() == height }

    private fun getScenicScore(lines: List<List<Int>>, height: Int): Int =
        lines.map { it.getScore(height) }.reduce { init, it -> init * it }

    private fun List<List<Int>>.getDirections(point: Pair<Int, Int>): List<List<Int>> {
        val line = this[point.first]
        val column = (0 until this.count()).map { this[it][point.second] }
        return listOf(
            line.slice(0..point.second).reversed(),
            line.slice(point.second until line.count()),
            column.slice(0..point.first).reversed(),
            column.slice(point.first until column.count())
        )
    }

    private fun List<Int>.getScore(height: Int): Int {
        this.slice(1 until this.count())
            .forEachIndexed() { index, p ->
                if (p >= height) return index + 1
            }
        return this.count() - 1
    }
}