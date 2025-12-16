package solutions.year2025

import DataFetcher
import solutions.Solution
import utils.Vec3
import utils.distance
import utils.toVec3
import kotlin.collections.toMutableMap

class Day8(override var dataFetcher: DataFetcher) : Solution {
    override val year: Int = 2025
    override val day: Int = 8
    override lateinit var inputAsList: List<String>
    override lateinit var input: String

    init {
        getInputData()
    }

    override fun partOneResult(): String {
        val boxes = inputAsList.mapIndexed { id, line ->
            id to line.toVec3()
        }.toSet()

        val maxCount = if (boxes.count() > 50) 1000 else 10

        val boxPairsByDist = BoxPairsByDist.new(boxes)

        val circuits: MutableMap<Int, Circuit> = boxes.associate { (id, _) -> id to Circuit(id) }.toMutableMap()
        var counter = 0
        while (maxCount > counter) {
            val (box1, box2) = boxPairsByDist.next().first
            val main = circuits.get(box1)!!.getMaster()
            val other = circuits.get(box2)!!.getMaster()
            main.unionIfNot(other)
            boxPairsByDist.removeConnected(box1, box2)
            counter++
        }

        return circuits.values.map { it.getMaster() }.toSet().map { it.count }.sortedBy { it }.reversed().take(3)
            .reduce { a, b -> a * b }.toString()
    }

    override fun partTwoResult(): String {
        val boxes = inputAsList.mapIndexed { id, line ->
            id to line.toVec3()
        }.toSet()

        val boxPairsByDist = BoxPairsByDist.new(boxes)

        val circuits: MutableMap<Int, Circuit> = boxes.associate { (id, _) -> id to Circuit(id) }.toMutableMap()
        while (true) {
            val (box1, box2) = boxPairsByDist.next().first
            val main = circuits.get(box1)!!.getMaster()
            val other = circuits.get(box2)!!.getMaster()
            main.unionIfNot(other)
            if (main.count == boxes.count())
                return (boxes.find { it.first == box1 }!!.second.x * boxes.find { it.first == box2 }!!.second.x)
                    .toInt()
                    .toString()
            boxPairsByDist.removeConnected(box1, box2)
        }
    }
}

private class Circuit(
    var value: Int
) {
    var root: Circuit
    var count = 1

    init {
        root = this
    }

    fun getMaster(): Circuit {
        return if (root.value == this.value) this else root.getMaster()
    }

    fun unionIfNot(other: Circuit) {
        if (this.getMaster() != other.getMaster()) {
            other.getMaster().root = this
            this.count = count + other.count
        }
    }
}

private class BoxPairsByDist private constructor(
    var collection: MutableMap<Pair<Int, Int>, Double>
) {
    fun next(): Pair<Pair<Int, Int>, Double> {
        val nextKey = collection.keys.first()
        val nextValue = collection.remove(nextKey)!!
        return nextKey to nextValue
    }

    fun removeConnected(firstBox: Int, secondBox: Int) {
        collection.remove(firstBox to secondBox)
        collection.remove(secondBox to firstBox)
    }

    companion object {
        fun new(boxes: Set<Pair<Int, Vec3>>): BoxPairsByDist =
            boxes.flatMap { (specId, specVec) ->
                boxes.filterNot { (nextId, _) -> nextId == specId }
                    .map { (nextId, nextVec) -> (specId to nextId) to specVec.distance(nextVec) }
            }.sortedBy { (_, dist) -> dist }
                .let { BoxPairsByDist(it.toMap().toMutableMap()) }


    }
}

