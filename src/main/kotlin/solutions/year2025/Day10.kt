package solutions.year2025

import DataFetcher
import solutions.Solution
import utils.combinations
import kotlin.text.split

class Day10(override var dataFetcher: DataFetcher) : Solution {
    override val year: Int = 2025
    override val day: Int = 10
    override lateinit var inputAsList: List<String>
    override lateinit var input: String

    init {
        getInputData()
    }

    override fun partOneResult(): String = inputAsList.map {
        it.replace("] (", "]-(")
            .replace(") {", ")-{")
            .split("-")
            .let {
                    it[0]
                        .replace("[", "")
                        .replace("]", "") to

                    it[1]
                        .replace("(", "")
                        .replace(")", "")
                        .split(" ")
                        .map { it
                            .split(",")
                            .map { it
                                .toInt() }
                            .toSet() }
            }
    }.sumOf { (lights, buttons) ->
        val targetPattern = lights.patternToInt()

        val buttonPatterns = buttons
            .map { button ->
                lights
                    .replace("#", ".")
                    .changeWithButton(button)
                    .patternToInt()
            }

        val validPatterns = buttonPatterns
            .combinations()
            .filter { it
                .fold(targetPattern) { acc, button -> acc xor button } == 0 }

        validPatterns.minBy { it.count() }.count()
    }.toString()

    override fun partTwoResult(): String = inputAsList.map {
        it.replace("] (", "]-(")
            .replace(") {", ")-{")
            .split("-")
            .let {
                    it[1]
                        .replace("(", "")
                        .replace(")", "")
                        .split(" ")
                        .map { it
                            .split(",")
                            .map { it
                                .toInt() }
                            .toSet() } to

                    it[2]
                        .split(",")
                        .map { it.toInt() }
            }
    }.map { (buttons, target) ->
        TODO()

    }.let { "hej" }


    private fun String.changeWithButton(button: Set<Int>): String {
        var result = this
        button.forEach { i ->
            val oldChar = result[i]
            when (oldChar) {
                '.' -> result = result.replaceRange(i, i + 1, "#")
                '#' -> result = result.replaceRange(i, i + 1, ".")
            }
        }

        return result
    }

    private fun List<Int>.countWithButton(button: Set<Int>): List<Int> {
        val result = this.toMutableList()
        button.forEach { i ->
            result[i]++
        }
        return result
    }

    fun String.patternToInt(): Int {
        val binary = this
            .replace('.', '0')
            .replace('#', '1')

        return binary.toInt(radix = 2)
    }
}


