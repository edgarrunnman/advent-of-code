package solutions.year2025

import DataFetcher
import solutions.Solution
import java.math.BigInteger

class Day7(override var dataFetcher: DataFetcher) : Solution {
    override val year: Int = 2025
    override val day: Int = 7
    override lateinit var inputAsList: List<String>
    override lateinit var input: String

    init {
        getInputData()
    }


    override fun partOneResult(): String {
        val startIndex = inputAsList.first().indexOf("S")
        val beams = mutableSetOf(startIndex)
        val folds = inputAsList.drop(1)
        var counter = 0
        folds.forEach { fold ->
            val newBeams = mutableSetOf<Int>()
            val removedBeams = mutableSetOf<Int>()

            beams.forEach { beam ->
                if (fold.get(beam) == '^') {
                    counter++
                    removedBeams.add(beam)
                    newBeams.add(beam - 1)
                    newBeams.add(beam + 1)
                }
            }
            removedBeams.forEach { removedBeam ->
                beams.remove(removedBeam)
            }
            beams.addAll(newBeams)
        }
        return counter.toString()
    }

    override fun partTwoResult(): String {
        val pathMapRev = getPathMap().reversed()
        val nodes: MutableMap<String, BigInteger> = mutableMapOf()

        pathMapRev.first().forEachIndexed { pn, pos ->
            if (pos == '|')
                nodes.put("0-$pn", 1.toBigInteger())
        }

        pathMapRev.drop(1).forEachIndexed { fn, fold ->
            fold.forEachIndexed { pn, pos ->
                if (pos == '|') {
                    val prevNodeId = "$fn-$pn"
                    val prevNode = nodes.get(prevNodeId) ?: error("node $prevNodeId not found")
                    val id = "${fn + 1}-$pn"
                    val newNode = prevNode
                    nodes.put(id, newNode)
                }

                if (pos == '^') {
                    val prevNodeIdFirst = "$fn-${pn - 1}"
                    val prevNodeIdSecond = "$fn-${pn + 1}"

                    val prevNodeFirst = nodes.get(prevNodeIdFirst) ?: error("node $prevNodeIdFirst not found")
                    val prevNodeSecond =
                        nodes.get(prevNodeIdSecond) ?: error("node $prevNodeIdSecond not found")

                    val id = "${fn + 1}-$pn"
                    val newNode =
                        prevNodeFirst + prevNodeSecond

                    nodes.put(id, newNode)
                }
            }
            val nodesToRemove = nodes.keys.filter { key -> key.startsWith("$fn-") }
            nodesToRemove.forEach {
                nodes.remove(it)
            }

        }
        return nodes.values.first().toString()
    }


    fun getPathMap(): List<String> {
        val folds = inputAsList.drop(1)
        var pathMap: List<String> = listOf()
        folds.forEachIndexed { fn, fold ->
            if (fn == 0) {
                pathMap = listOf(inputAsList.first().replace("S", "|"))
            }

            if (fn > 0) {
                val prevFold = pathMap[fn - 1]
                val foldBuilder = StringBuilder(fold)
                fold.forEachIndexed { pn, p ->
                    if (prevFold[pn] == '|' && p != '^') {
                        foldBuilder.replace(pn, pn + 1, "|")
                    }

                    if (prevFold[pn] == '^') {
                        foldBuilder.replace(pn - 1, pn + 2, "|.|")
                    }
                }
                pathMap = pathMap + foldBuilder.toString()
            }

        }
        return pathMap
    }
}

