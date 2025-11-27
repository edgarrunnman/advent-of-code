package solutions.year2024

import DataFetcher
import solutions.Solution
import kotlin.math.abs

class Day2(override var dataFetcher: DataFetcher) : Solution {
    override val year: Int = 2024
    override val day: Int = 2
    override lateinit var inputAsList: List<String>
    override lateinit var input: String

    init {
        getInputData()
    }

    override fun partOneResult(): String =
        inputAsList.count { rapport ->
            rapport.toLevels().let { it.isSorted() && it.correctStepSize() }
        }.toString()

    override fun partTwoResult(): String =
        inputAsList.count { rapport ->
            rapport.toLevels().let { (it.isSorted() && it.correctStepSize()) || it.ifCorrected()}

        }.toString()

    private fun String.toLevels() = this.split(" ").map { it.toInt() }
    private fun List<Int>.isSorted() = this == this.sorted() || this == this.sorted().reversed()
    private fun List<Int>.correctStepSize() = this.windowed(2).all { abs(it.first() - it.last()) in 1..3 }
    private fun List<Int>.ifCorrected() = this.filterIndexed { index, _ ->
        this.filterIndexed { inner, _ -> index != inner }.let { it.isSorted() && it.correctStepSize() }
    }.isNotEmpty()

}
