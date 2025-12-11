package solutions.year2025

import DataFetcher
import solutions.Solution
import utils.toVec3

class Day8(override var dataFetcher: DataFetcher) : Solution {
    override val year: Int = 2025
    override val day: Int = 8
    override lateinit var inputAsList: List<String>
    override lateinit var input: String

    init {
        getInputData()
    }

    override fun partOneResult(): String {
        val boxesWithDist = inputAsList.mapIndexed { n, line ->
            n to line.toVec3()
        }
        val maxCount = if (boxesWithDist.count() > 50) 1000 else 10

        val shortestConnections = boxesWithDist.map { (id, vec) ->
            boxesWithDist.filterNot { it == id }
        }


    }

    override fun partTwoResult(): String = TODO()
}

