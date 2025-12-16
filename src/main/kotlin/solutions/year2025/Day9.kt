package solutions.year2025

import DataFetcher
import solutions.Solution
import utils.Vec3
import utils.area
import utils.distance
import utils.toVec2
import utils.toVec3
import kotlin.collections.toMutableMap

class Day9(override var dataFetcher: DataFetcher) : Solution {
    override val year: Int = 2025
    override val day: Int = 9
    override lateinit var inputAsList: List<String>
    override lateinit var input: String

    init {
        getInputData()
    }

    override fun partOneResult(): String =
        inputAsList.map { it.toVec2() }.let {
            it.flatMap { p1 ->
                it.map { p2 ->
                    p1.area(p2)
                }
            }.max().toString()
        }

    override fun partTwoResult(): String = TODO()
}


