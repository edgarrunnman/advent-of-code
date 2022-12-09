package solutions.year2022

import DataFetcher
import solutions.Solution

class Day9 (override var dataFetcher: DataFetcher) : Solution {
    override val year: Int = 2022
    override val day: Int = 9
    override lateinit var inputAsList: List<String>
    override lateinit var input: String

    init {
        getInputData()
    }

    override fun partOneResult(): String {
        val list = mutableListOf<String>()
        inputAsList.getMoves()
        .fold(List(2) {Knot(0,0)}) {
            rope,direction -> direction.makeMove(rope, list)
        }
        return list.distinct().count().toString()
    }

    override fun partTwoResult(): String {
        val list = mutableListOf<String>()
        inputAsList.getMoves()
            .fold(List(10) {Knot(0,0)}) {
                    rope,direction -> direction.makeMove(rope, list)
            }
        return list.distinct().count().toString()
    }

    private fun List<String>.getMoves(): List<Char> {
        return this.map { it.getDirections() }
            .flatMap { it.asIterable() }
    }

    private fun String.getDirections(): List<Char> {
        var direction = this.split(" ")[0].first()
        var count = this.split(" ")[1].toInt()
        return (0 until count).toList().map { direction }
    }

    class Knot(val x: Int, val y: Int){
        override fun toString(): String {
            return "${x}:${y}"
        }
    }

    private fun Char.makeMove(rope: List<Knot>,list: MutableList<String>): List<Knot> {
        var newRope = rope.toMutableList()
        var head = when (this) {
            'R' -> Knot(newRope[0].x, newRope[0].y + 1)
            'L' -> Knot(newRope[0].x, newRope[0].y - 1)
            'U' -> Knot(newRope[0].x +1, newRope[0].y)
            'D' -> Knot(newRope[0].x -1, newRope[0].y)
            else -> newRope[0]
        }
        newRope[0] = head
        newRope = updateRope(newRope).toMutableList()
        list.add(newRope.last().toString())
        return newRope
    }

    private fun updateRope(rope: List<Knot>): List<Knot> {
        val newRope = rope.toMutableList()
        (0 until newRope.count()- 1).forEach() { index ->
            if (!isTouching(newRope[index], newRope[index + 1]))
                newRope[index + 1] = move(newRope[index + 1], newRope[index])
        }
        return newRope
    }
    private fun isSameLine(head: Knot, tail: Knot): Boolean {
        return head.x == tail.x || head.y == tail.y
    }
    private fun isTouching(head: Knot, tail: Knot): Boolean {

        (-1 .. 1).forEach { dx ->
            (-1 .. 1).forEach { dy ->
                if (head.x + dx == tail.x && head.y + dy == tail.y) return true
            }
        }
        return false
    }

    private fun move(tail: Knot, head: Knot): Knot {
        if(!isTouching(tail, head)) {
            if(isSameLine(tail, head)) {
                if (isTouching(Knot(tail.x, tail.y -1), head)) return Knot(tail.x, tail.y -1)
                if (isTouching(Knot(tail.x, tail.y +1), head)) return Knot(tail.x, tail.y + 1)
                if (isTouching(Knot(tail.x - 1, tail.y), head)) return Knot(tail.x - 1, tail.y)
                if (isTouching(Knot(tail.x + 1, tail.y), head)) return Knot(tail.x + 1, tail.y)
            }
            if (isTouching(Knot(tail.x - 1, tail.y -1), head)) return Knot(tail.x - 1, tail.y -1)
            if (isTouching(Knot(tail.x - 1, tail.y + 1), head)) return Knot(tail.x - 1, tail.y + 1)
            if (isTouching(Knot(tail.x + 1, tail.y -1), head)) return Knot(tail.x + 1, tail.y -1)
            if (isTouching(Knot(tail.x + 1, tail.y +1), head)) return Knot(tail.x + 1, tail.y +1)
        }
        return Knot(tail.x, tail.y)
    }
}