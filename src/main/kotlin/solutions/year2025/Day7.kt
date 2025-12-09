package solutions.year2025

import DataFetcher
import solutions.Solution

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
        val manyfolds = inputAsList.drop(1)
        var counter = 0
        manyfolds.forEach { manyfold ->
            val newBeams = mutableSetOf<Int>()
            val removedBeams = mutableSetOf<Int>()

            beams.forEach { beam ->
                if (manyfold.get(beam) == '^') {
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

    override fun partTwoResult(): String = TODO()
}

