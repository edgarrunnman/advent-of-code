package solutions.year2025

import DataFetcher
import solutions.Solution
import utils.area
import utils.isInsideOrOnBoundary
import utils.polygonFromBreakpoints
import utils.polygonFromRectangle
import utils.toCoordinate
import utils.toVec2

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

    override fun partTwoResult(): String {
        val coordinates = inputAsList.map { it.toCoordinate() }

        val polygon = polygonFromBreakpoints(coordinates)

        val rectangles = coordinates.let {
            it.flatMap { p1 ->
                it.mapNotNull { p2 ->
                    if (p1 == p2) null
                    if (p1.x == p2.x || p1.y == p2.y) null
                    else polygonFromRectangle(p1, p2)
                }
            }
        }
        val validRect = rectangles.filter { isInsideOrOnBoundary(polygon, it) }

        return validRect.maxBy { it.area }.coordinates.let { coordinates ->
            val a = coordinates[0]
            val b = coordinates[2]
            a.area(b)
        }.toString()

    }
}


