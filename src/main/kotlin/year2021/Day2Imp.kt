package year2021
import Solution
import Solution.Companion.getInputData

class Day2Imp: Solution {
    override val year: Int = 2021
    override val day: Int = 2
    override var puzzleInput: List<String> = getInputData(this.year, this.day)

    override fun solution1(): String =
        puzzleInput.fold(Pair(0,0)) { crd, move -> calcCrd(crd, move)}
            .let { it.first * it.second }.toString()

    override fun solution2(): String =
        puzzleInput.fold(Triple(0,0,0)) { crd, move -> calcCrdWithAim(crd, move)}
            .let { it.first * it.second }.toString()

    private fun calcCrd(crd: Pair<Int, Int>, move: String): Pair<Int, Int> =
        when (move.split(" ").first()) {
            "forward" -> Pair(crd.first + move.split(" ")[1].toInt(), crd.second)
            "up" -> Pair(crd.first, crd.second - move.split(" ")[1].toInt())
            "down" -> Pair(crd.first, crd.second + move.split(" ")[1].toInt())
            else -> crd
        }
    private fun calcCrdWithAim(crd: Triple<Int, Int, Int>, move: String): Triple<Int, Int, Int> =
        when (move.split(" ").first()) {
            "forward" -> Triple(
                crd.first + move.split(" ")[1].toInt(),
                crd.second + crd.third * move.split(" ")[1].toInt(),
                crd.third)
            "up" -> Triple(crd.first, crd.second, crd.third - move.split(" ")[1].toInt())
            "down" -> Triple(crd.first, crd.second, crd.third + move.split(" ")[1].toInt())
            else -> crd
        }
}