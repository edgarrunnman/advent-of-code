package solutions.year2021

import DataFetcher
import solutions.Solution

class Day5(override var dataFetcher: DataFetcher) : Solution {
    override val year: Int = 2021
    override val day: Int = 5
    override lateinit var inputAsList: List<String>
    override lateinit var input: String

    init {
        getInputData()
    }

    //implement first solution
    override fun partOneResult(): String {
        var foo = inputAsList
            .map { it.getStartEndPoints() }
            .filterHorVer()
            .fold(emptyList<Point>()) { list, it -> list.plus(getLinePoints(it)) }
            .let { duplicates(it) }
        return foo.toString()
    }

    //implement second solution
    override fun partTwoResult(): String = "not rdy"

    private fun String.getStartEndPoints(): Line {
        var foo = this.split(" -> ").map {
            it.split(",").let { pair -> Point(pair[0].toInt(), pair[1].toInt()) }
        }.let { Line(it[0], it[1]) }
        return foo
    }

    private fun List<Line>.filterHorVer(): List<Line> {
        var foo = this.filter {
            (it.startPoint.x == it.endPoint.x && it.startPoint.y != it.endPoint.y)
                    || (it.startPoint.x != it.endPoint.x && it.startPoint.y == it.endPoint.y)
        }
        return foo
    }

    private fun getLinePoints(line: Line): List<Point> {
        var foo = (0 .. if (line.startPoint.x != line.endPoint.x)
            kotlin.math.abs(line.startPoint.x - line.endPoint.x)
        else
            kotlin.math.abs(line.startPoint.y - line.endPoint.y)
                )
        var foo2 = foo.map {
            Point(
                line.startPoint.x + it * ((line.endPoint.x - line.startPoint.x) / (foo.last + 1)),
                line.startPoint.y + it * ((line.endPoint.y - line.startPoint.x) / (foo.last + 1))
            )
        }

        return foo2
    }

    private fun duplicates(points: List<Point>): Int {
        var foo = points.filter { item -> points.count { it == item } > 1 }.toSet().distinct().count()
        return foo
    }

}

data class Point(val x: Int, val y: Int)
data class Line(val startPoint: Point, val endPoint: Point)