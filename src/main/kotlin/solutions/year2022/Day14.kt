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

    //    private var xMin = 0
    private var yMax = 0
    private var yMin = 0

    init {
        getInputData()
    }

    override fun partOneResult(): String {
        var regex = "\\d+,\\d+".toRegex()
        var foo = regex.findAll(input).map { it.value.split(",") }.map { Pair(it[1], it[0]) }
        xMax = foo.maxBy { it.first.toInt() }.first.toInt()
//        xMin = foo.minBy { it.first.toInt() }.first.toInt()
        yMax = foo.maxBy { it.second.toInt() }.second.toInt()
        yMin = foo.minBy { it.second.toInt() }.second.toInt()
        var map = (0..xMax).map { x -> (yMin..yMax).map { y -> MapPoint(x, y, '.') }.toMutableList() }
        inputAsList.forEach { it.drawLines(map) }
        var counter = 0
       while(pourSand(map)){
           counter++
       }
            printMap(map)


        return counter.toString()
    }

    override fun partTwoResult(): String {
        var foo = inputAsList
        return foo.toString()
    }

    private fun pourSand(map: Map14): Boolean {
        val pourPoint = Point(0, 500 - yMin)
        var npD = Point(pourPoint.x, pourPoint.y)
        while (map[npD.x][npD.y].fyll == '.') {
            npD.x++
            var cP = Point(npD.x, npD.y)

            if (npD.x >= xMax || npD.y < 0 || npD.y > yMax - yMin) return false
            var symbolD = map[npD.x][npD.y].fyll
            if (symbolD != '.') {
                var symbolL = map[npD.x][npD.y - 1].fyll
                var symbolR = map[npD.x][npD.y + 1].fyll
                if (symbolL == '.') {
                    npD.y--
                } else if (symbolR == '.') {
                    npD.y++
                } else {
                    map[cP.x][cP.y].fyll = 'o'
                }
            }
        }
        return true
    }

    private fun String.drawLines(map: Map14) {
        var regex = "\\d+,\\d+".toRegex()
        var points =
            regex.findAll(this).map { it.value.split(",") }.map { Point(it[1].toInt(), it[0].toInt()) }.toList()
        var pointsCount = points.count()
        (0 until pointsCount - 1).forEach() { index ->
            var xFrom = points[index].x
            var xTo = points[index + 1].x
            var yFrom = points[index].y
            var yTo = points[index + 1].y
            if (xFrom == xTo) {
                (0..abs(yTo - yFrom)).forEach { step ->
                    var positive = yFrom < yTo
                    var xMap = xFrom
                    var yMap = if (positive) yFrom + step - yMin else yFrom - step - yMin
                    map[xMap][yMap].fyll = 'X'
                }
            }
            if (yFrom == yTo) {
                (0..abs(xTo - xFrom)).forEach { step ->
                    var positive = xFrom < xTo
                    var xMap = if (positive) xFrom + step else xFrom - step
                    var yMap = yFrom - yMin
                    map[xMap][yMap].fyll = 'X'
                }
            }
        }
    }

    private fun printMap(map: Map14): String {
        var string = StringBuilder()
        map.forEach { line ->
            line.forEach { point ->
                print(" ${point.fyll} ")
                string.append(point.fyll)
            }
            println()
            string.appendLine()
        }
        println()
        string.appendLine()
        return string.toString()
    }

    data class Point(var x: Int, var y: Int)
    data class MapPoint(val x: Int, val y: Int, var fyll: Char)

}
typealias Map14 = List<MutableList<Day14.MapPoint>>



