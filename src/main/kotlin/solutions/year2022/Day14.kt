package solutions.year2022

import DataFetcher
import solutions.Solution
import kotlin.math.abs

class Day14(override var dataFetcher: DataFetcher) : Solution {
    override val year: Int = 2022
    override val day: Int = 14
    override lateinit var inputAsList: List<String>
    override lateinit var input: String

    private var xMax = 0
    private var yMax = 0
    private var yMin = 0

    init {
        getInputData()
    }

    override fun partOneResult(): String {
        var regex = "\\d+,\\d+".toRegex()
        var foo = regex.findAll(input).map { it.value.split(",") }.map { Pair(it[1], it[0]) }
        xMax = foo.maxBy { it.first.toInt() }.first.toInt()
        yMax = foo.maxBy { it.second.toInt() }.second.toInt()
        yMin = foo.minBy { it.second.toInt() }.second.toInt()
        val map = (0..xMax).map { (yMin..yMax).map { '.' }.toMutableList() }
        inputAsList.forEach { it.drawLines(map) }
        var counter = 0
        while (pourSand(map)) {
            counter++
        }
        return counter.toString()
    }

    override fun partTwoResult(): String {
        var regex = "\\d+,\\d+".toRegex()
        var foo = regex.findAll(input).map { it.value.split(",") }.map { Pair(it[1], it[0]) }
        xMax = foo.maxBy { it.first.toInt() }.first.toInt() + 2
        yMax = 500 + xMax
        yMin = 500 - xMax
        val map = (0..xMax).map { x ->
            (yMin..yMax).map {
                if (x == xMax) 'X' else '.'
            }.toMutableList()
        }
        inputAsList.forEach { it.drawLines(map) }
        var counter = 0
        while (pourSand(map)) {
            counter++
        }
        return counter.toString()
    }

    private fun pourSand(map: Map14): Boolean {
        val pourPoint = Point(0, 500 - yMin)
        val npD = Point(pourPoint.x, pourPoint.y)

        if (map[npD.x][npD.y] != '.') return false
        while (map[npD.x][npD.y] == '.') {
            val cP = Point(npD.x, npD.y)
            npD.x++
            if (npD.x > xMax || npD.y - 1 < 0 || npD.y + 1 > yMax - yMin) return false
            val symbolD = map[npD.x][npD.y]
            if (symbolD != '.') {
                if (map[npD.x][npD.y - 1] == '.')
                    npD.y--
                else if (map[npD.x][npD.y + 1] == '.')
                    npD.y++
                else
                    map[cP.x][cP.y] = 'o'
            }
        }
        return true
    }

    private fun String.drawLines(map: Map14) {
        val regex = "\\d+,\\d+".toRegex()
        val points = regex
            .findAll(this)
            .map { it.value.split(",") }
            .map { Point(it[1].toInt(), it[0].toInt()) }
            .toList()
        val pointsCount = points.count()
        (0 until pointsCount - 1).forEach() { index ->
            val xFrom = points[index].x
            val xTo = points[index + 1].x
            val yFrom = points[index].y
            val yTo = points[index + 1].y
            if (xFrom == xTo)
                (0..abs(yTo - yFrom)).forEach { step ->
                    val positive = yFrom < yTo
                    val yMap = if (positive)
                        yFrom + step - yMin
                    else
                        yFrom - step - yMin
                    map[xFrom][yMap] = 'X'
                }
            if (yFrom == yTo)
                (0..abs(xTo - xFrom)).forEach { step ->
                    val positive = xFrom < xTo
                    val xMap = if (positive)
                        xFrom + step
                    else
                        xFrom - step
                    val yMap = yFrom - yMin
                    map[xMap][yMap] = 'X'
                }
        }
    }

    data class Point(var x: Int, var y: Int)
}
typealias Map14 = List<MutableList<Char>>



