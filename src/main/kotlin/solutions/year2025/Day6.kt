package solutions.year2025

import DataFetcher
import solutions.Solution
import java.math.BigInteger
import kotlin.plus
import kotlin.times

class Day6(override var dataFetcher: DataFetcher) : Solution {
    override val year: Int = 2025
    override val day: Int = 6
    override lateinit var inputAsList: List<String>
    override lateinit var input: String

    init {
        getInputData()
    }


    override fun partOneResult(): String {
        val grid = inputAsList.map { it.split(" ").filterNot { it.isEmpty() } }

        val result = (0 until grid.first().size).map { x ->
            val numbers = mutableListOf<BigInteger>()
            (0 until grid.size - 1).map { y ->
                numbers.add(grid[y][x].toBigInteger())
            }
            numbers.reduce { a, b ->
                when (grid.last()[x]) {
                    "*" -> a * b
                    "+" -> a + b
                    else -> error("Should not happen")
                }
            }
        }.sumOf { it }

        return result.toString()
    }

    override fun partTwoResult(): String {

        val digitsCountIndexet =
            (inputAsList.last() + " ")
                .split("*", "+")
                .filter { it.isNotEmpty() }
                .map { it.length + 1 }


        val numberCellStack =
            inputAsList.dropLast(1)
                .map {
                    (it + "x").replace(" ", "x")
                        .let { line ->
                            val numberCells = mutableListOf<String>()
                            var newLine = line
                            digitsCountIndexet.forEach { digitCount ->
                                val take = newLine.take(digitCount)
                                newLine = newLine.substring(digitCount)
                                numberCells.add(take)
                            }
                            numberCells
                        }
                }

        val operations = inputAsList.last().split(" ").filter { it.isNotEmpty() }


        return digitsCountIndexet.mapIndexed { cellIndex, cellSize ->
            val values = (0 until cellSize).reversed().mapNotNull { digitIndex ->
                var number = StringBuilder()

                numberCellStack.forEach { numberLine ->
                    val digit = numberLine[cellIndex][digitIndex]
                    when (digit) {
                        'x' -> {}
                        else -> {
                            number.append(digit)
                        }
                    }
                }
                if (number.isEmpty()) {
                    null
                } else
                    number.toString().toBigInteger()
            }
            values.reduce { a, b ->
                when (operations[cellIndex]) {
                    "*" -> a * b
                    "+" -> a + b
                    else -> error("Should not happen")
                }
            }

        }.sumOf { it }.toString()
    }
}
