package solutions.year2025

import DataFetcher
import solutions.Solution
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
        val boxesWithDist = inputAsList.mapIndexed { id, line ->
            id to line.toVec3()
        }
        val maxCount = if (boxesWithDist.count() > 50) 1000 else 10

        var shortestConnections = boxesWithDist.flatMap { (specId, specVec) ->
            boxesWithDist.filterNot { (nextId, _) -> nextId == specId }
                .map { (nextId, nextVec) -> (specId to nextId) to specVec.distance(nextVec) }
        }.sortedBy { (_, dist) -> dist }.map { it.first }.toMutableList()

        var counter = 1
        val results = boxesWithDist.associate { it.first to mutableSetOf(it.first) }
        while (maxCount > counter) {
//            shortestConnections.forEach { (id, connections) ->
//                println(id to connections)
//            }
//            println()
//            println()
            val (specB1, specB2) = shortestConnections.first()


//            val group1 = results.singleOrNull { group -> group.contains(specB1) }
//            val group2 = results.singleOrNull { group -> group.contains(specB2) }
            val group1 = results[specB1]!!
            val group2 = results[specB2]!!
//            println("grp1 $group1 - grp2 $group2")
            when {
                group1.isNotEmpty() && group2.isNotEmpty() -> {
                    group1.addAll(group2)
                    group2.clear()
                    continue
                }
                group1.isEmpty() && group2.isEmpty() -> {
                    results.add(mutableSetOf(specB1, specB2))
                    counter++
                }

                group1 != null && group2 == null -> {
                    group1.add(specB2)
                    counter++
                }

                group1 == null && group2 != null -> {
                    group2.add(specB1)
                    counter++
                }

                group1 != null && group2 != null -> {

                    group1.addAll(group2)
                    group2.clear()
                    counter++
                }
            }
            shortestConnections = shortestConnections.filterNot { (b1, _) -> b1 == specB1 }.toMutableList()
//            results.forEach {
//                println(it)
//            }
//            println()
        }

//        shortestConnections.forEach { (id, connections) ->
//            println(id to connections)
//        }
        results.sortedBy { it.count() }.reversed().forEach {
            println(it)
        }
        return results.map{ it.count()}.sortedBy { it }.reversed().take(3).reduce { a, b -> a * b }.toString()
    }

    override fun partTwoResult(): String = TODO()
}

