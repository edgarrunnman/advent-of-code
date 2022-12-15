package solutions.year2022

import DataFetcher
import solutions.Solution
import kotlin.math.abs

class Day15(override var dataFetcher: DataFetcher) : Solution {
    override val year: Int = 2022
    override val day: Int = 15
    override lateinit var inputAsList: List<String>
    override lateinit var input: String

    init {
        getInputData()
    }

    override fun partOneResult(): String {
        var line = 10
        var foo = inputAsList.map { it.getSB() }
        var foo2 = foo.filter { it.inLine(line) }
        var foo3 = foo2.getAllLineCoverage(line)
        var foo4 = foo3.distinct().sorted()
        return (foo4.count()).toString()
    }

    override fun partTwoResult(): String {
        var foo = inputAsList
        return foo.toString()
    }


//    private fun SB.printSB(line: Int){
//        val xS = this.s.x
//        val xB = this.b.x
//        val yS = this.s.y
//        val yB = this.b.y
//        val size = abs(xB - xS) + abs(yB - yS)
//
//        (0 .. size * 2).map { line ->
//            (0..size * 2).map { point ->
//
//            }
//        }
//
//
//
//
//        val maxCov = abs(yS - yB)
//        val lineCov = maxCov - dy + abs(xS- xB)
//        val result = mutableListOf<Int>()
//        (0..lineCov).forEach {
//            result.add(it + this.s.x)
//            result.add(0 - it + this.s.x)
//        }
//        return result
//    }

    data class Point(var x: Int, var y: Int)
    data class SB(var s: Point, var b: Point)

    private fun String.getSB(): SB {
        val xRegex = "x=-*\\d+".toRegex()
        val yRegex = "y=-*\\d+".toRegex()
        val stringSB = this.split(": ")
        val s = Point(
            xRegex.find(stringSB[0])!!.value.split("=")[1].toInt(),
            yRegex.find(stringSB[0])!!.value.split("=")[1].toInt()
        )
        val b = Point(
            xRegex.find(stringSB[1])!!.value.split("=")[1].toInt(),
            yRegex.find(stringSB[1])!!.value.split("=")[1].toInt()
        )
        return SB(s, b)
    }

    private fun SB.inLine(lineIndex: Int): Boolean {
        val xS = this.s.x
        val xB = this.b.x
        val yS = this.s.y
        val yB = this.b.y
        val dx = abs(xB - xS)
        val dy = abs(yB - yS)
        var yMax = 0
        var yMin = 0
        if (dx < dy) {
            yMax = yB
            yMin = yS - dy
        } else {
            yMax = yS + dx
            yMin = yS - dx
        }
        return lineIndex in (yMin..yMax)
    }

    private fun SB.getLineCoverage(lineIndex: Int): List<Int> {
        val xS = this.s.x
        val xB = this.b.x
        val yS = this.s.y
        val yB = this.b.y
        val dy = abs(this.s.y - lineIndex)
        val maxCov = abs(yS - yB)
        val lineCov = maxCov - dy + abs(xS- xB)
        val result = mutableListOf<Int>()
        (0..lineCov).forEach {
            result.add(it + this.s.x)
            result.add(0 - it + this.s.x)
        }
        return result
    }

    private fun List<SB>.getAllLineCoverage(lineIndex: Int): List<Int> {
        val result = mutableListOf<Int>()
        this.forEach { result.addAll(it.getLineCoverage(lineIndex)) }
        return result
    }
}


