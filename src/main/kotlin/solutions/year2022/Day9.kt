package solutions.year2022

import DataFetcher
import solutions.Solution

class Day9(override var dataFetcher: DataFetcher) : Solution {
    override val year: Int = 2022
    override val day: Int = 9
    override lateinit var inputAsList: List<String>
    override lateinit var input: String

    init {
        getInputData()
    }

    override fun partOneResult(): String =
        inputAsList.getMoves()
            .fold(Rope(List(2) { Knot(0, 0) })) { rope, direction ->
                direction.makeMove(rope)
            }.tailMoves.count().toString()

    override fun partTwoResult(): String =
        inputAsList.getMoves()
            .fold(Rope(List(10) { Knot(0, 0) })) { rope, direction ->
                direction.makeMove(rope)
            }.tailMoves.count().toString()

    data class Knot(val x: Int, val y: Int) {
    }

    data class Rope(val knots: List<Knot>) {
        var tailMoves: Set<Knot> = setOf()
    }

    private fun List<String>.getMoves(): List<Char> {
        return this.map { it.getDirections() }
            .flatMap { it.asIterable() }
    }

    private fun String.getDirections(): List<Char> {
        val direction = this.split(" ")[0].first()
        val count = this.split(" ")[1].toInt()
        return (0 until count).toList().map { direction }
    }

    private fun Char.makeMove(rope: Rope): Rope {
        val knots = rope.knots.toMutableList()
        val head = when (this) {
            'R' -> Knot(knots[0].x, knots[0].y + 1)
            'L' -> Knot(knots[0].x, knots[0].y - 1)
            'U' -> Knot(knots[0].x + 1, knots[0].y)
            'D' -> Knot(knots[0].x - 1, knots[0].y)
            else -> knots[0]
        }
        knots[0] = head
        val newRope = Rope(knots.updateKnots())
        newRope.tailMoves = rope.tailMoves + newRope.knots.last()
        return newRope
    }

    private fun List<Knot>.updateKnots(): List<Knot> {
        val newRope = this.toMutableList()
        (0 until newRope.count() - 1).forEach() { index ->
            if (!isTouching(newRope[index], newRope[index + 1]))
                newRope[index + 1] = move(newRope[index + 1], newRope[index])
        }
        return newRope
    }

    private fun isSameLine(head: Knot, tail: Knot): Boolean {
        return head.x == tail.x || head.y == tail.y
    }

    private fun isTouching(head: Knot, tail: Knot): Boolean {

        (-1..1).forEach { dx ->
            (-1..1).forEach { dy ->
                if (head.x + dx == tail.x && head.y + dy == tail.y) return true
            }
        }
        return false
    }

    private fun move(tail: Knot, head: Knot): Knot {
        if (!isTouching(tail, head)) {
            if (isSameLine(tail, head)) {
                if (isTouching(Knot(tail.x, tail.y - 1), head)) return Knot(tail.x, tail.y - 1)
                if (isTouching(Knot(tail.x, tail.y + 1), head)) return Knot(tail.x, tail.y + 1)
                if (isTouching(Knot(tail.x - 1, tail.y), head)) return Knot(tail.x - 1, tail.y)
                if (isTouching(Knot(tail.x + 1, tail.y), head)) return Knot(tail.x + 1, tail.y)
            }
            if (isTouching(Knot(tail.x - 1, tail.y - 1), head)) return Knot(tail.x - 1, tail.y - 1)
            if (isTouching(Knot(tail.x - 1, tail.y + 1), head)) return Knot(tail.x - 1, tail.y + 1)
            if (isTouching(Knot(tail.x + 1, tail.y - 1), head)) return Knot(tail.x + 1, tail.y - 1)
            if (isTouching(Knot(tail.x + 1, tail.y + 1), head)) return Knot(tail.x + 1, tail.y + 1)
        }
        return Knot(tail.x, tail.y)
    }
}