package solutions.year2022

import DataFetcher
import solutions.Solution
import java.util.StringJoiner

class Day12(override var dataFetcher: DataFetcher) : Solution {
    override val year: Int = 2022
    override val day: Int = 12
    override lateinit var inputAsList: List<String>
    override lateinit var input: String
    var register = mutableListOf<String>()

    init {
        getInputData()
    }

    override fun partOneResult(): String {
        var map = inputAsList.map { line ->
            line.map { point -> point }
        }
        var pathsLst = mutableListOf<Int>()
        var startPosition =
            map.mapIndexed() { x, line -> line.mapIndexed() { y, point -> Pair(point, Position(x, y)) } }
                .flatMap { it.asIterable() }.first() { it.first == 'S' }
        map.move(startPosition.second, Path(0, listOf()), pathsLst)
        return pathsLst.min().toString()

    }

    override fun partTwoResult(): String {
        return "hej"
    }

    private fun getPathChar(list: List<Position>, map: Map): String {
        return list.map { map[it.x][it.y] }.joinToString("")
    }

    private fun getPathPoints(list: List<Position>, map: Map): String {
        return list.map { "${it.x}:${it.y}," }.joinToString("")
    }

    data class Position(var x: Int, var y: Int)

    class Path(var steps: Int, var visitedPositions: List<Position>)

    private fun Map.move(position: Position, path: Path, list: MutableList<Int>, initSteps: Int = 0) {
        val visitedPositions = path.visitedPositions + position
        val legitMoves = getLegitMoves(visitedPositions, this)
        legitMoves.forEach { pos ->
//            register.add(getPathPoints(visitedPositions, this))
//            print(register.distinct().count())
//            print("-")
//            print(register.count())
//            println()
            println(visitedPositions.count())
            if (this[pos.x][pos.y] == 'E') {
                val count = path.visitedPositions.count() + 1
                if (list.isEmpty()) list.add(count)
                if (path.steps + 1 < list.min()) list.add(count)
            } else {
                this.move(pos, Path(path.steps + 1, visitedPositions), list)
            }
        }
    }

    private fun getLegitMoves(visitedPositions: List<Position>, map: Map): List<Position> {
        val pos = visitedPositions.last()
        var height = map[pos.x][pos.y]
        if (height == 'S') height = 'a'
        val maxHeight = height + 1
        val legitPositions = mutableListOf<Position>()
        if (pos.x > 0) {
            var nextHeight = map[pos.x - 1][pos.y]
            if (nextHeight == 'E') nextHeight = 'z'
            if (nextHeight <= maxHeight)
                legitPositions.add(Position(pos.x - 1, pos.y))
        }

        if (pos.x < map.count() - 1) {
            var nextHeight = map[pos.x + 1][pos.y]
            if (nextHeight == 'E') nextHeight = 'z'
            if (nextHeight <= maxHeight)
                legitPositions.add(Position(pos.x + 1, pos.y))
        }

        if (pos.y > 0) {
            var nextHeight = map[pos.x][pos.y - 1]
            if (nextHeight == 'E') nextHeight = 'z'
            if (nextHeight <= maxHeight)
                legitPositions.add(Position(pos.x, pos.y - 1))
        }

        if (pos.y < map[pos.x].count() - 1) {
            var nextHeight = map[pos.x][pos.y + 1]
            if (nextHeight == 'E') nextHeight = 'z'
            if (nextHeight <= maxHeight)
                legitPositions.add(Position(pos.x, pos.y + 1))
        }
        return legitPositions.filter { !visitedPositions.contains(it) }
    }
}

typealias Map = List<List<Char>>


