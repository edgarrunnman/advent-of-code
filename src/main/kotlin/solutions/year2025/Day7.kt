package solutions.year2025

import DataFetcher
import solutions.Solution
import utils.print

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
        val pathMapRev = getPathMap_2().reversed()
        pathMapRev.print()

        val nodes: MutableMap<String, Node> = mutableMapOf()

        pathMapRev.first().forEachIndexed { pn, pos ->
            if (pos == '|') {
                val newNode = Node.Last(id = "0-$pn", pathToEnd = mutableListOf("$pn"))
                nodes.put("0-$pos", newNode)
            }
        }

        pathMapRev.drop(1).forEachIndexed { fn, fold ->
            fold.forEachIndexed { pn, pos ->
                if (pos == '|') {
                    val priviusNodeId = "$fn-$pn"
                    val priviusNode = nodes.get(priviusNodeId) ?: error("node $priviusNodeId not found")
                    val id = "${fn + 1}-$pn"
                    val newNode =
                        Node.Single(id = id, next = priviusNode, pathToEnd = priviusNode.pathToEnd.map { "$pn.it" })
                    nodes.put(id, newNode)

                }

                if (pos == '^') {
                    val priviusNodeIdFirst = "${fn - 1}-$pn"
                    val priviusNodeIdSecond = "${fn + 1}-$pn"
                    val priviusNodeFirst = nodes.get(priviusNodeIdFirst) ?: error("node $priviusNodeIdFirst not found")
                    val priviusNodeSecond = nodes.get(priviusNodeIdSecond) ?: error("node $priviusNodeIdSecond not found")

                    val id = "${fn + 1}-$pn"
                    val newNode =
                        Node.Two(id = id, nextLeft = priviusNodeFirst, nextRight = priviusNodeSecond, pathToEnd = priviusNodeFirst.pathToEnd.map { "$pn.it" } + priviusNodeSecond.pathToEnd.map { "$pn.it" })
                    nodes.put(id, newNode)
                    }
                }
            }



        return "hej"
    }

    abstract class Node(
        var id: String,
        val parents: List<Node> = emptyList(),
        val pathFromStart: List<String>,
        val pathToEnd: List<String>,
        var ready: Boolean = false
    ) {

        class Last(
            parents: List<Node> = emptyList(),
            id: String,
            pathFromStart: List<String> = listOf(),
            pathToEnd: List<String> = listOf(),
        ) : Node(id, parents, pathFromStart, pathToEnd)

        class Single(
            val next: Node,
            id: String,
            parents: List<Node> = emptyList(),
            pathFromStart: List<String> = listOf(),
            pathToEnd: List<String>,
        ) : Node(id, parents, pathFromStart, pathToEnd)

        class Two(
            val nextLeft: Node,
            val nextRight: Node,
            id: String,
            parents: List<Node> = emptyList(),
            pathFromStart: List<String> = listOf(),
            pathToEnd: List<String>,
        ) : Node(id, parents, pathFromStart, pathToEnd)


        fun isReady() {
            ready = true
        }
    }

    fun getPathMap(): List<String> {

        val startIndex = inputAsList.first().indexOf("S")
        val splits = mutableSetOf(startIndex)
        val folds = inputAsList.drop(1)
        val pathMap = mutableListOf(inputAsList.first().replace("S", "|"))
        folds.forEach { fold ->
            val newBeams = mutableSetOf<Int>()
            val removedSplits = mutableSetOf<Int>()
            val previusPathFold = pathMap[pathMap.lastIndex].replace("^", ".")
            var nextPathFold = StringBuilder().append(previusPathFold)

            splits.forEach { s ->
                if (fold.get(s) == '^') {
                    removedSplits.add(s)
                    newBeams.add(s - 1)
                    newBeams.add(s + 1)
                    nextPathFold.replace(s - 1, s, "|")
                    nextPathFold.replace(s, s + 1, "^")
                    nextPathFold.replace(s + 1, s + 2, "|")
                }
            }
            val nextPathFoldString = nextPathFold.toString()
            pathMap.add(nextPathFoldString)
            removedSplits.forEach { removedSplit ->
                splits.remove(removedSplit)
            }
            splits.addAll(newBeams)
        }
        pathMap.print()
        return pathMap
    }
    fun getPathMap_2(): List<String> {
        val folds = inputAsList.drop(1)
        var pathMap: List<String>  = listOf()
        folds.forEachIndexed {fn, fold ->
            if (fn == 0) {
                pathMap = listOf(inputAsList.first().replace("S", "|"))
            }

            if (fn > 0) {
                val prevFold = pathMap[fn-1]
                val foldBuilder = StringBuilder(fold)
                fold.forEachIndexed { pn, p ->
                    if (prevFold[pn] == '|' && p != '^' ) {
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

