package solutions.year2022

import DataFetcher
import solutions.Solution
import java.lang.StringBuilder

class Day12(override var dataFetcher: DataFetcher) : Solution {
    override val year: Int = 2022
    override val day: Int = 12
    override lateinit var inputAsList: List<String>
    override lateinit var input: String
    init {
        getInputData()
    }

    override fun partOneResult(): String {
        var map = inputAsList.map { line ->
            line.map { height -> Point(height) }
        }
        var startPosition =
            map.mapIndexed() { x, line -> line.mapIndexed() { y, point -> Pair(point, Position(x, y)) } }
                .flatMap { it.asIterable() }.first() { it.first.height == 'E' }
        map.move2(startPosition.second)
        var endPosition =
            map.mapIndexed() { x, line -> line.mapIndexed() { y, point -> Pair(point, Position(x, y)) } }
                .flatMap { it.asIterable() }.first() { it.first.height == 'S' }

        return endPosition.first.steps.toString()
    }

    override fun partTwoResult(): String {
        var map = inputAsList.map { line ->
            line.map { height -> Point(height) }
        }
        var startPosition =
            map.mapIndexed() { x, line -> line.mapIndexed() { y, point -> Pair(point, Position(x, y)) } }
                .flatMap { it.asIterable() }.first() { it.first.height == 'E' }
        map.move2(startPosition.second)
        var endPosition =
            map.mapIndexed() { x, line -> line.mapIndexed() { y, point -> Pair(point, Position(x, y)) } }
                .flatMap { it.asIterable() }.filter { it.first.height == 'a' }.map { it.first.steps }.filter { it != null }.sortedBy { it }.first()

        return endPosition.toString()
    }

    private fun getStepsMap(map: Map): String {
        var string = StringBuilder()
        map.forEach { line ->
            line.forEach { point ->
                string.append(if (point.steps == null) point.height else point.steps)
            }
            string.appendLine()
        }
        return string.toString()
    }

    private fun Map.move2(pos: Position) {
        var step = 1
        var legitMoves = markNeighbours(pos, this, step)
        while (legitMoves.count() != 0) {
            step++
            var newLegitMoves = listOf<Position>()
            legitMoves.forEach { nextPositon ->
                newLegitMoves = newLegitMoves + markNeighbours(nextPositon, this, step)
            }
            var stepsMap = getStepsMap(this)
            legitMoves = newLegitMoves
        }
    }

    private fun markNeighbours(pos: Position, map: Map, steps: Int) : List<Position>{
        var height = map[pos.x][pos.y].height
        if (height == 'E') height = 'z'
        val minHeight = height - 1
        var legitPositions = mutableListOf<Position>()
        if (pos.x > 0) {
            var point = map[pos.x - 1][pos.y]
            if (point.steps == null || point.steps!! > steps){
                var nextHeight = point.height
                if (nextHeight == 'S') nextHeight = 'a'
                if (nextHeight >= minHeight){
                    map[pos.x - 1][pos.y].steps = steps
                    legitPositions.add(Position(pos.x - 1, pos.y))
                }
            }
        }

        if (pos.x < map.count() - 1) {
            var point = map[pos.x + 1][pos.y]
            if (point.steps == null || point.steps!! > steps){
                var nextHeight = point.height
                if (nextHeight == 'S') nextHeight = 'a'
                if (nextHeight >= minHeight){
                    map[pos.x + 1][pos.y].steps = steps
                    legitPositions.add(Position(pos.x + 1, pos.y))
                }
            }
        }

        if (pos.y > 0) {
            var point = map[pos.x][pos.y - 1]
            if (point.steps == null || point.steps!! > steps){
                var nextHeight = point.height
                if (nextHeight == 'S') nextHeight = 'a'
                if (nextHeight >= minHeight){
                    map[pos.x][pos.y - 1].steps = steps
                    legitPositions.add(Position(pos.x, pos.y - 1))
                }
            }
        }

        if (pos.y < map[pos.x].count() - 1) {
            var point = map[pos.x][pos.y + 1]
            if (point.steps == null || point.steps!! > steps){
                var nextHeight = point.height
                if (nextHeight == 'S') nextHeight = 'a'
                if (nextHeight >= minHeight){
                    map[pos.x][pos.y + 1].steps = steps
                    legitPositions.add(Position(pos.x, pos.y + 1))
                }
            }
        }
        return legitPositions
    }
}
data class Position(var x: Int, var y: Int)
data class Point(val height: Char ){
    var steps: Int? = null
}
typealias Map = List<List<Point>>


