package solutions.year2025

import DataFetcher
import solutions.Solution
import java.math.BigInteger

class Day3(override var dataFetcher: DataFetcher) : Solution {
    override val year: Int = 2025
    override val day: Int = 3
    override lateinit var inputAsList: List<String>
    override lateinit var input: String

    init {
        getInputData()
    }

    override fun partOneResult(): String =
        inputAsList.sumOf {
//            it.getJoltageOf(2)
            solveByViktor(2, it)!!.toBigInteger()
        }.toString()


    override fun partTwoResult(): String =
        inputAsList.sumOf {
//            it.getJoltageOf(12)
            solveByViktor(12, it)!!.toBigInteger()
        }.toString()


    private fun String.getJoltageOf(of: Int): BigInteger {
        return this.biggestOf(of, of).toBigInteger()
    }

    private fun String.biggestOf(of: Int, size: Int): String {
        if (of == 0) return this
        val candidates = this.windowed(of).mapIndexed { index, w -> index to w }.biggest()

        val biggest = candidates.map { (index, w) ->
            (w.first() + this.substring(index + 1)
                .biggestOf(of - 1, size))
                .let {
                    if (it.length > size)
                        it.substring(0, size)
                    else it
                }
        }.maxOf { it.toBigInteger() }
        return biggest.toString()
    }

    private fun List<Pair<Int, String>>.biggest() =
        this.groupBy {
            it.second.first()
        }
            .toSortedMap().values.last()


    private tailrec fun solveByViktor(size: Int, input: String, prefix: String = ""): String? {
        if (size <= 0) return prefix
        if(input.length == size) return prefix+input
        if(input.length < size) return null
        val candidates= input
            .dropLast(size-1)
            .asSequence()
            .withIndex()
            .map { indexedValue -> indexedValue.map{ it.digitToInt()} }
        var largest = candidates.first()
        for (c in candidates.drop(1)) {
            if (largest.value==9) break
            if (c.value > largest.value) {
                largest = c
            }
        }
        return solveByViktor(size-1, input.drop(largest.index+1), prefix="$prefix${largest.value}")
    }

    private fun <T, U> IndexedValue<T>.map(f: (T) -> U) = IndexedValue(index, f(value))
}