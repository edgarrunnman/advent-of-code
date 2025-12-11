package solutions.year2025

import DataFetcher
import solutions.Solution

class Day8(override var dataFetcher: DataFetcher) : Solution {
    override val year: Int = 2025
    override val day: Int = 8
    override lateinit var inputAsList: List<String>
    override lateinit var input: String

    init {
        getInputData()
    }

    override fun partOneResult(): String {
        val points = inputAsList.mapIndexed{n, line ->
            println("$n: $line")
            n to line.toVec3()}

        var tmpPoints = points.toMutableList()
        var resultPoints : MutableList<Pair<Pair<Int, Int>, Double>> = mutableListOf()
        while (tmpPoints.isNotEmpty()) {

            val (k, v) = tmpPoints.first()

            tmpPoints = tmpPoints.drop(1).toMutableList()

            val newPoint = points.filterNot { (k2, _) -> k2 == k }.toList().map { (k2, v2) ->
                Pair(k, k2) to distance(v, v2)}.minBy { it -> it.second }
            resultPoints.add(newPoint)
        }

//        val dist = (0 .. points.count()/2).map {
//           val (k, v) = tmpPoints.first()
//            tmpPoints = tmpPoints.drop(1).toMutableList()
//            points.filterNot { (k2, _) -> k2 == k }.toList().map { (k2, v2) ->
//                Pair(k, k2) to distance(v, v2)}.minBy { it -> it.second }
//        }

//        val dist = points.map { (k, v) ->
//            points.filterNot { (k2, _) -> k2 == k }.toList().map { (k2, v2) ->
//                val pair = if (k > k2) Pair(k2, k) else Pair(k, k2)
//                pair to distance(v, v2)}.minBy { it -> it.second }
//
//        }
        resultPoints.sortedBy{ (_, dist) -> dist}.forEach {
            println(it)
        }
        println()
        val groups : MutableSet<MutableSet<Int>> = mutableSetOf()
        resultPoints.distinctBy { (_, dist) -> dist }.sortedBy{it.second}.map { it.first}.forEach { (f, s) ->
            val group = groups.find { group -> f in group || s in group }
            if (group == null) {
                groups.add(mutableSetOf(f, s))
                println("$f and $s to new group [$f, $s]")
            }
            else {
                group.addAll(listOf(f, s))
                println("$f and $s to $group")
            }

        }
        return groups.map {
            it.count() }.sortedBy { it }.also { println(it) }.takeLast(3).reduce {a,b -> a*b}.toString()

    }

    override fun partTwoResult(): String = TODO()

    private data class Vec3(val x: Double, val y: Double, val z: Double)

    private fun distance(a: Vec3, b: Vec3): Double =
        kotlin.math.sqrt(
            (a.x - b.x).let { it * it } +
                    (a.y - b.y).let { it * it } +
                    (a.z - b.z).let { it * it }
        )
    private fun String.toVec3() = this.split(",").let { Vec3(it.get(0).toDouble(), it.get(1).toDouble(), it.get(2).toDouble()) }
}

