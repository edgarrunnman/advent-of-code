package solutions.year2022

import DataFetcher
import TimeLogger
import solutions.Solution
import kotlin.math.abs
import kotlin.properties.Delegates

class Day15(override var dataFetcher: DataFetcher) : Solution {
    override val year: Int = 2022
    override val day: Int = 15
    override lateinit var inputAsList: List<String>
    override lateinit var input: String

    init {
        getInputData()
    }

    override fun partOneResult(): String {
//        var line = 10L
//        var line = 2000000L
//        var foo = inputAsList.map { it.getSB() }
//        var foo2 = foo.filter { it.overlap(line) }
//        var foo5 = foo2.getAllLineCoverageSet(line)
//        var foo6 = foo5.filter { !foo.filter { sb -> sb.b.y == line }.map { sb -> sb.b.x }.contains(it) }
//        return foo6.count().toString()
//        return "hej"
    }

    override fun partTwoResult(): String {
        var foo = inputAsList.map { it.getSB() }
        var size = 4000000
        var line = 0L
        (1..size).forEach { y ->
            if (y % 10000 == 0) println(y)
            var foo2 = foo.filter { sb -> y < sb.yMax && y > sb.yMin }
                .map { sb -> sb.getRangeX(y) }
                .sortedBy { it.first }
            var foo3 = foo2.allPointsWithinSpan(0, size)

            if (!foo3) {
                var bigX = 0L
                var currentMax = foo2.first().second
                foo2.slice(1 until foo2.count()).forEach() { sb ->
                    if (currentMax < sb.first) bigX = currentMax + 1L
                    if (sb.second > currentMax) currentMax = sb.second
                }
                var foo6 = bigX * 4000000 + y
                line = foo6
            }

        }

        return line.toString()

    }

    private fun List<Pair<Int, Int>>.allPointsWithinSpan(xmin: Int, xmax: Int): Boolean {
        if (this.first().first > xmin) return false
        var currentMax = this.first().second
        this.slice(1 until this.count()).forEachIndexed() { index, sb ->
            if (currentMax < sb.first) return false
            if (sb.second > currentMax) currentMax = sb.second
        }
        if (currentMax < xmax) return false
        return true
    }

    private fun List<SB>.checkSBes(yMin: Int, yMax: Int, xMin: Int, xMax: Int): Int {
        var counter = 0
        (yMin..yMax).forEach { y ->
            (xMin..xMax).forEach { x ->
//                if ((y % ((yMax)/100000) == 0L) && (x % ((yMax)/10) == 0L)) println("checking point ${y}:${x}")
                var canBeEmpty = true
                var iterator = 0
                while (iterator < this.count() && canBeEmpty) {
                    var thisCanBeTrue = !this[iterator].checkPoint(y, x)
                    canBeEmpty = thisCanBeTrue
                    iterator++
                }
                if (canBeEmpty)
                    counter++
            }
        }
        return counter
    }

    private fun SB.checkPoint(yGiven: Int, xGiven: Int): Boolean {
        val xS = this.s.x
        val yS = this.s.y
        val xB = this.b.x
        val yB = this.b.y
        val size = this.radius
        val withinY = yGiven >= yS - size && yGiven <= yS + size
        if (withinY) {
            val withinX = xGiven >= xS - size && xGiven <= xS + size
            if (withinX) {
                return abs(xS - xGiven) + abs(yS - yGiven) <= size
            }
        }
        return false
    }


    data class Point(var x: Int, var y: Int)
    data class SB(var s: Point, var b: Point, var radius: Int) {
        var yMax by Delegates.notNull<Int>()
        var yMin by Delegates.notNull<Int>()

        init {
            yMax = s.y + radius
            yMin = s.y - radius
        }

        fun getRangeX(y: Int): Pair<Int, Int> {
            var min = s.x - (radius - abs(s.y - y))
            var max = s.x + (radius - abs(s.y - y))
            return Pair(min, max)
        }
    }

    private fun String.getSB(): SB {
        val xRegex = "x=-*\\d+".toRegex()
        val yRegex = "y=-*\\d+".toRegex()
        val stringSB = this.split(": ")
        val xS = xRegex.find(stringSB[0])!!.value.split("=")[1].toInt()
        val yS = yRegex.find(stringSB[0])!!.value.split("=")[1].toInt()
        val xB = xRegex.find(stringSB[1])!!.value.split("=")[1].toInt()
        val yB = yRegex.find(stringSB[1])!!.value.split("=")[1].toInt()
        val size = abs(xB - xS) + abs(yB - yS)
        val s = Point(
            xRegex.find(stringSB[0])!!.value.split("=")[1].toInt(),
            yRegex.find(stringSB[0])!!.value.split("=")[1].toInt()
        )
        val b = Point(
            xRegex.find(stringSB[1])!!.value.split("=")[1].toInt(),
            yRegex.find(stringSB[1])!!.value.split("=")[1].toInt()
        )
        return SB(s, b, size)
    }
}


