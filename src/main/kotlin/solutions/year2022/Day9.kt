package solutions.year2022

import DataFetcher
import solutions.Solution
import java.text.FieldPosition

class Day9(override var dataFetcher: DataFetcher) : Solution {
    override val year: Int = 2022
    override val day: Int = 9
    override lateinit var inputAsList: List<String>
    override lateinit var input: String

    init {
        getInputData()
    }

    override fun partOneResult(): String {
        var board: Board = MutableList(5) { MutableList(6) { false } }
        var board2: Board = mutableListOf(mutableListOf(false))
        var foo = inputAsList.getMoves()
        var foo2 = foo.slice(0 until foo.count())
        var nextDir = foo[1]
        var postCounter = 2
        var lastPosition = Pair(0, 0)
        var currenPosition = Pair(0,0)
        foo2.forEach {
            println("position before: ${lastPosition}")
            lastPosition = makeMove(it, nextDir, lastPosition, board)
            printBoard(board, lastPosition)
            println("position after: ${lastPosition}")
            println("${it} -> ${nextDir}")
            println()
            println()
            println()
            nextDir = foo[postCounter++]

        }
        var foo3 = board.flatMap { it.asIterable() }.filter { visited -> visited }
        printBoard(board, lastPosition)

        return foo3.count().toString()
    }

    override fun partTwoResult(): String {
        var foo = inputAsList
        return foo.toString()
    }

    fun printBoard(board: Board, head: Pair<Int, Int>) {
        board.reversed().forEachIndexed { x, line ->
            line.forEachIndexed { y, point ->
//                print(if (Pair(line.count() - 1 - x, y) == head) "H" else if (point) "X" else "+")
                print(if (point) "X" else "+")
            }
            println()
        }
    }

    fun List<String>.getMoves(): List<Char> {
        return this.map { it.getDirections() }
            .flatMap { it.asIterable() }
    }

    fun String.getDirections(): List<Char> {
        var direction = this.split(" ")[0].first()
        var count = this.split(" ")[1].toInt()
        return (0 until count).toList().map { direction }

    }
data class Point(val x: Int, val y: Int){
    constructor(point: Point): this(point.x, point.y)
}
//    fun makeMove2(current: Char, next: Char, positioning: Pair<Point, Point>, board: Board): Pair<Int, Int> {
////        var newPosition = lastPosition
//        val newPositioning = Pair(Point(positioning.first), Point(positioning.second))
//        if (current == 'R') {
//            newPositioning.second.y = newPositioning.second.y + 1
//            if (!isDiagonalMove(current, next))
//                board[newPosition.first][newPosition.second] = true
//        }
//        if (current == 'L') {
//            newPosition = Pair(newPosition.first, newPosition.second - 1)
//            if (!isDiagonalMove(current, next)) board[newPosition.first][newPosition.second] = true
//        }
//        if (current == 'U') {
//            newPosition = Pair(newPosition.first + 1, newPosition.second)
//            if (!isDiagonalMove(current, next)) board[newPosition.first][newPosition.second] = true
//        }
//        if (current == 'D') {
//            newPosition = Pair(newPosition.first - 1, newPosition.second)
//            if (!isDiagonalMove(current, next)) board[newPosition.first][newPosition.second] = true
//        }
//        return newPosition
//    }
    fun makeMove(current: Char, next: Char, lastPosition: Pair<Int, Int>, board: Board): Pair<Int, Int> {
        var newPosition = lastPosition
        if (current == 'R') {
            newPosition = Pair(newPosition.first, newPosition.second + 1)
            if (!isDiagonalMove(current, next))
                board[newPosition.first][newPosition.second] = true
        }
        if (current == 'L') {
            newPosition = Pair(newPosition.first, newPosition.second - 1)
            if (!isDiagonalMove(current, next)) board[newPosition.first][newPosition.second] = true
        }
        if (current == 'U') {
            newPosition = Pair(newPosition.first + 1, newPosition.second)
            if (!isDiagonalMove(current, next)) board[newPosition.first][newPosition.second] = true
        }
        if (current == 'D') {
            newPosition = Pair(newPosition.first - 1, newPosition.second)
            if (!isDiagonalMove(current, next)) board[newPosition.first][newPosition.second] = true
        }
        return newPosition
    }

    fun isDiagonalMove(l: Char, n: Char): Boolean {
        return when {
            l == 'R' && n == 'U' -> true
            l == 'R' && n == 'D' -> true
            l == 'L' && n == 'U' -> true
            l == 'L' && n == 'D' -> true
            l == 'U' && n == 'R' -> true
            l == 'D' && n == 'R' -> true
            l == 'U' && n == 'L' -> true
            l == 'D' && n == 'L' -> true
            else -> false
        }
    }
}

typealias Board = MutableList<MutableList<Boolean>>


