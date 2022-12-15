package solutions.year2022

import DataFetcher
import solutions.Solution
import java.util.StringJoiner
import kotlin.math.abs

class Day15(override var dataFetcher: DataFetcher) : Solution {
    override val year: Int = 2022
    override val day: Int = 15
    override lateinit var inputAsList: List<String>
    override lateinit var input: String
    var symbolCoverage = '+'
    var symbolEmpty = '.'
    var symbolLine = '-'
    var symbolOnLine = 'O'

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
        return "hej"
    }

    override fun partTwoResult(): String {
        var foo = inputAsList.map { it.getSB() }
        var yMax = 4000000L
        var xMax = 4000000L
        var steps= 100000
//        var yMax = 20L
//        var xMax = 20L
//        var steps= 2L
        var count = 0L
        (1 .. steps).forEach { y -> (1..steps).forEach { x ->
            println("on step ${y}-${x}")
            var step = xMax/steps
            var set = mutableSetOf<String>()
            var yMin = step * y - step + 1
            var yMax = step * y
            var xMin = step * x - step + 1
            var xMax = step * x

            foo.forEach { set.addAll(it.getLineCoverage2(yMin, yMax, xMin, xMax))}
            count += step * step - set.count()
            println("count after step: ${count}")

        }}
return count.toString()

    }

    private fun SB.getLineCoverage2(yMin: Long, yMax: Long, xMin: Long, xMax: Long) : Set<String>{
        val xS = this.s.x
        val xB = this.b.x
        val yS = this.s.y
        val yB = this.b.y
        val size = abs(xB - xS) + abs(yB - yS)
val set = mutableSetOf<String>()
        (-size..size).forEach { y ->
            (-size..size).forEach { x ->
                if (x in (-(size - abs(y))..(size - abs(y)))) {
                    var yResult = size - (size - yS) + y
                    var xResult = size - (size - xS) + x
                    if (yResult in (yMin..yMax) && xResult in (xMin..xMax)){

                        set.add( "${size - (size - yS) + y}:${size - (size - xS) + x}")
                    }
                }
            }
        }

//        var board = (-size..size).map { y ->
//            (-size..size).map { x ->
//                if (x in (-(size - abs(y))..(size - abs(y)))) symbolCoverage else
//                    symbolEmpty
//            }.toMutableList()
//        }.toMutableList()

//        board.forEach { y ->
//            y.forEach { x ->
//                print(x)
//            }
//            println()
//        }
//        println(set)

//        (-size..size).forEach() { x ->
//            if (x in (-(size - abs(lineIndex))..(size - abs(lineIndex))))
//                result.add(x - dx)
//        }
return set

    }

    data class Point(var x: Long, var y: Long) {
//        override fun toString(): String {
//            return "${x}:${y}"
//        }
    }

    data class SB(var s: Point, var b: Point) {
//        override fun toString(): String {
//            return "S ${s}, B ${b}"
//        }
    }

    private fun String.getSB(): SB {
        val xRegex = "x=-*\\d+".toRegex()
        val yRegex = "y=-*\\d+".toRegex()
        val stringSB = this.split(": ")
        val s = Point(
            xRegex.find(stringSB[0])!!.value.split("=")[1].toLong(),
            yRegex.find(stringSB[0])!!.value.split("=")[1].toLong()
        )
        val b = Point(
            xRegex.find(stringSB[1])!!.value.split("=")[1].toLong(),
            yRegex.find(stringSB[1])!!.value.split("=")[1].toLong()
        )
        return SB(s, b)
    }

    private fun SB.overlap(lineIndex: Long): Boolean {
        val xS = this.s.x
        val xB = this.b.x
        val yS = this.s.y
        val yB = this.b.y
        val dx = abs(xB - xS)
        val dy = abs(yB - yS)
        val yMax = yS + if (dx < dy) dy else dx
        val yMin = yS - if (dx < dy) dy else dx
        return lineIndex in (yMin..yMax)
    }

    private fun SB.getLineCoverage(line: Long): Set<Long> {
        val xS = this.s.x
        val xB = this.b.x
        val yS = this.s.y
        val yB = this.b.y
        val dy = abs(yS - line)
        val maxCov = abs(yS - yB)
        val lineCov = maxCov - dy + abs(xS - xB)
        val result = mutableSetOf<Long>()
        (0..lineCov).forEach {
            result.add(it + xS)
            result.add(0 - it + xS)
        }
//        println(result.sorted())
//        println()
        return result
    }


    private fun List<SB>.getAllLineCoverage(line: Long): List<Long> {
        val result = mutableListOf<Long>()
        this.forEach { result.addAll(it.getLineCoverage(line)) }
        return result
    }

    private fun List<SB>.getAllLineCoverageSet(line: Long): Set<Long> {
        val result = mutableSetOf<Long>()
        this.forEach {
            result.addAll(it.getLineCoverage(line))
        }
        return result
    }

    private fun SB.printSB(line: Long = -1) {
        val xS = this.s.x
        val xB = this.b.x
        val yS = this.s.y
        val yB = this.b.y
        val size = abs(xB - xS) + abs(yB - yS)

        var board = (-size..size).map { y ->
            (-size..size).map { x ->
                if (x in (-(size - abs(y))..(size - abs(y)))) symbolCoverage else
                    symbolEmpty
            }.toMutableList()
        }.toMutableList()

        if (line != -1L) {

            var lineIndex = line - yS + size

            board[lineIndex.toInt()] =
                board[lineIndex.toInt()].map {
                    if (it == symbolEmpty) symbolLine else
                        it
                }
                    .toMutableList()
        }

        val s = Point(xS - (xS - size), yS - (yS - size))
        val b = Point(xB - (xB - size) + (xB - xS), yB - (yB - size) + (yB - yS))
        board[s.y.toInt()][s.x.toInt()] = 'S'
        board[b.y.toInt()][b.x.toInt()] = 'B'
        board.forEach { y ->
            y.forEach { x ->
                print(x)
            }
            println()
        }
        println(this)
    }

    private fun List<SB>.printBoard(line: Long, printOne: Int = -1) {
        var mainBoard: MutableList<MutableList<Char>> = MutableList(40) { MutableList(40) { symbolEmpty } }
        this.forEachIndexed() { sbIndex, sb ->
            val xS = sb.s.x
            val xB = sb.b.x
            val yS = sb.s.y
            val yB = sb.b.y
            val size = abs(xB - xS) + abs(yB - yS)

            var board = (-size..size).map { y ->
                (-size..size).map { x ->
                    if (x in (-(size - abs(y))..(size - abs(y)))) symbolCoverage else
                        symbolEmpty
                }.toMutableList()
            }.toMutableList()
            var lineIndex = line - yS + size
//            if (lineIndex in ((yS - size)..(yS + size))) {
//
//                board[lineIndex.toInt()] =
//                    board[lineIndex.toInt()].map {
//                        if (it == symbolEmpty) symbolLine else
//                            symbolOnLine
//                    }
//                        .toMutableList()
//            }
            val yOffset = yS - size
            val xOffset = xS - size
            val s = Point(xS - (xS - size), yS - (yS - size))
            val b = Point(xB - (xB - size) + (xB - xS), yB - (yB - size) + (yB - yS))
            board[s.y.toInt()][s.x.toInt()] = 'S'
            board[b.y.toInt()][b.x.toInt()] = 'B'

            board.forEachIndexed() { yIndex, y ->
                y.forEachIndexed() { xIndex, x ->
                    if (mainBoard[yIndex + yOffset.toInt() + 10][xIndex + xOffset.toInt() + 10] != symbolOnLine) {

                        mainBoard[yIndex + yOffset.toInt() + 10][xIndex + xOffset.toInt() + 10] =
                            if (x != symbolEmpty && printOne >= 0 && x != symbolLine && sbIndex == printOne)
                                x
                            else if (x != symbolEmpty && x != symbolLine && x != symbolCoverage)
                                x
                            else if (x != symbolEmpty && x != symbolLine && printOne < 0)
                                x
                            else
                                mainBoard[yIndex + yOffset.toInt() + 10][xIndex + xOffset.toInt() + 10]
                    }
                }
            }


        }

        mainBoard[20] =
            mainBoard[20].map {
                if (it == symbolEmpty) symbolLine else
                    symbolOnLine
            }
                .toMutableList()

        mainBoard.forEach { y ->
            y.forEach { x ->
                print(x)
            }
            println()
        }
        println(this)
    }
}


