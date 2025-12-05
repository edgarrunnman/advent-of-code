package solutions.year2025

import DataFetcher
import utils.normalizeRanges
import solutions.Solution

class Day5(override var dataFetcher: DataFetcher) : Solution {
    override val year: Int = 2025
    override val day: Int = 5
    override lateinit var inputAsList: List<String>
    override lateinit var input: String

    init {
        getInputData(keepLineBreaks = true)
    }

    override fun partOneResult(): String {
        val (ranges, ids) = input.split("\n\n").let { groups ->
            groups.first().split("\n").map { rangeLine ->
                rangeLine.split("-").let { range ->
                    val start = range.first()
                    val end = range.last()
                    start.toBigInteger()..end.toBigInteger()
                }
            } to
                    groups.last().split("\n").filterNot { it.isEmpty() }.map { id ->
                        id.toBigInteger()
                    }
        }
        return ids.count { id -> ranges.any { range -> id in range } }.toString()
    }

    override fun partTwoResult(): String {
        val ranges = input.split("\n\n").let { groups ->
            groups.first().split("\n").map { rangeLine ->
                rangeLine.split("-").let { range ->
                    range.first().toBigInteger()..range.last().toBigInteger()
                }
            }
        }
        val finalRanges = normalizeRanges(ranges)
        return (finalRanges.map { it.endInclusive - it.start + 1.toBigInteger()}.sumOf { it }).toString()
    }

}

