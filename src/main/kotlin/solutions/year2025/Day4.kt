package solutions.year2025

import DataFetcher
import neighborsOf
import solutions.Solution
import kotlin.collections.getOrNull

class Day4(override var dataFetcher: DataFetcher) : Solution {
    override val year: Int = 2025
    override val day: Int = 4
    override lateinit var inputAsList: List<String>
    override lateinit var input: String

    init {
        getInputData()
    }

    override fun partOneResult(): String =
        inputAsList.flatMapIndexed { x, line ->
            line.mapIndexed { y, cell ->
                cell == '@' && inputAsList.map { it.toList() }.canRemoveRoll(x, y)
            }
        }.count { it }.toString()


    override fun partTwoResult(): String =
        inputAsList.map { it.toList() }.rollsRemoved().toString()

    private fun List<List<Char>>.canRemoveRoll(x: Int, y: Int): Boolean =
        neighborsOf(x, y).map { (sx, sy) -> this.getOrNull(sx)?.getOrNull(sy) == '@' }.count { it } < 4

    private tailrec fun List<List<Char>>.rollsRemoved(total: Int = 0): Int {
        val state = this.toMutableList().map { it.toMutableList() }
        val count = this.flatMapIndexed { x, line ->
            line.mapIndexed { y, cell ->
                if (cell == '@' && this.canRemoveRoll(x, y)) {
                    state[x][y] = 'x'
                    true
                } else false

            }
        }.count { it }

        if (count == 0) return total
        return state.rollsRemoved(count + total)
    }
}
