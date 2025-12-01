package solutions.year2025

import DataFetcher
import solutions.Solution

class Day1(override var dataFetcher: DataFetcher) : Solution {
    override val year: Int = 2025
    override val day: Int = 1
    override lateinit var inputAsList: List<String>
    override lateinit var input: String

    init {
        getInputData()
    }

    override fun partOneResult(): String =
        inputAsList.fold(50 to 0) { acc, line ->
            val rotation = line.toRotation()
            val pos = acc.first.rotate(rotation)
            if (pos == 0)
                pos to acc.second + 1
            else
                pos to acc.second
        }.second.toString()

    override fun partTwoResult(): String =
        inputAsList.fold(50 to 0) { acc, line ->
            val rotation = line.toRotation()
            if (acc.first.jumpsZero(rotation)) {
                acc.first.rotate(rotation) to acc.second + 1 + rotation.second.fulRotations()
            } else {
                acc.first.rotate(rotation) to acc.second + rotation.second.fulRotations()
            }
        }.second.toString()


    private fun Int.rotate(rotation: Pair<LeftOrRight, Int>) =
        when (rotation.first) {
            LeftOrRight.L -> (this + (rotation.second % 100)) % 100
            LeftOrRight.R -> (this + 100 - (rotation.second % 100)) % 100
        }
    private fun Int.fulRotations(): Int = this / 100

    private fun Int.jumpsZero(rotation: Pair<LeftOrRight, Int>) =
        when (rotation.first) {
            LeftOrRight.L -> this.let { if (it == 100) 0 else it } + (rotation.second % 100) >= 100
            LeftOrRight.R -> this.let { if (it == 0) 100 else it } - (rotation.second % 100) <= 0
        }
    private fun String.toRotation() =
        when {
            this.startsWith("R") -> LeftOrRight.R to this.substringAfter("R").toInt()
            this.startsWith("L") -> LeftOrRight.L to this.substringAfter("L").toInt()
            else -> throw Error("Invalid rotation $this")
        }

    enum class LeftOrRight {
        L,
        R,
    }
}
